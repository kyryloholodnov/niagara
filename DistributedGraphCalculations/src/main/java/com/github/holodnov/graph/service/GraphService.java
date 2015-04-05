package com.github.holodnov.graph.service;

import com.github.holodnov.algorithms.graph.DirectedGraph;
import com.github.holodnov.algorithms.graph.Edge;
import com.github.holodnov.graph.generator.UINT64Generator;
import com.github.holodnov.graph.zoo.NoZooNodeException;
import com.github.holodnov.graph.zoo.ZooClient;
import com.github.holodnov.graph.zoo.ZooException;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static com.github.holodnov.graph.service.Status.COMPLETED;
import static com.github.holodnov.graph.service.Status.UNCOMPLETED;
import static com.github.holodnov.graph.zoo.ZooClient.acquireLock;
import static com.github.holodnov.graph.zoo.ZooClient.releaseLock;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.System.getProperty;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.Executors.newSingleThreadExecutor;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static javax.xml.bind.DatatypeConverter.printHexBinary;
import static org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type.CHILD_ADDED;
import static org.apache.curator.utils.PathUtils.validatePath;

/**
 * @author Kyrylo Holodnov
 */
public class GraphService implements InitializingBean, DisposableBean {

    private static final Logger log = LoggerFactory.getLogger(GraphService.class);
    private static final long RANGE_COUNT = 1L << 10;
    private static final long OFFSET_READING = 1L << 22;
    private static final long MIN_RANGE_STEP = OFFSET_READING;

    private ZooClient zooClient;
    private UINT64Generator uint64Generator;
    private final String graphPath;
    private final int nThreads;
    private final ExecutorService graphProcessingExecutor;
    private final ExecutorService nodeProcessingExecutor;

    public GraphService() {
        this(getProperty("zookeeper.graph.root.path", "/graphs"),
                3 * Runtime.getRuntime().availableProcessors());
    }

    public GraphService(String graphPath, int nThreads) {
        this.graphPath = validatePath(graphPath);
        this.nThreads = nThreads;
        graphProcessingExecutor = newFixedThreadPool(nThreads);
        nodeProcessingExecutor = newSingleThreadExecutor();
    }

    public void setZooClient(ZooClient zooClient) throws Exception {
        this.zooClient = zooClient;
    }

    public void setUint64Generator(UINT64Generator uint64Generator) {
        this.uint64Generator = uint64Generator;
    }

    public String sendDAGForestMaxWeightDistributed(DirectedGraph graph) throws ZooException, InterruptedException {
        long start = System.currentTimeMillis();
        String graphId = uint64Generator.generate();
        String completedPath = graphPath + "/dag_forest_max_weight/completed/" + graphId;
        String graphDataPath = completedPath + "/graph_data";
        String startTimePath = completedPath + "/start_time";
        String lockPath = completedPath + "/lock";
        if (!zooClient.blockUntilConnectedOrTimedOut()) {
            throw new ZooException("Cannot connect to ZooKeeper (connection timed out)");
        }
        zooClient.createPath(graphDataPath, toByteArrayMaxWeightForestDAGGraph(graph));
        zooClient.createPath(startTimePath, start);
        zooClient.createPath(lockPath);
        String calculationsPath = graphPath + "/dag_forest_max_weight/calculations/" + graphId;
        long fullRange = 1L << graph.getVertexCount();
        long step = max(fullRange / RANGE_COUNT, MIN_RANGE_STEP);
        for (int i = 0; ; i++) {
            if (i * step > fullRange) {
                break;
            }
            String rangePath = calculationsPath + "/range_" + (i + 1);
            zooClient.createPath(rangePath + "/left", i * step);
            zooClient.createPath(rangePath + "/right", min((i + 1) * step, fullRange));
            zooClient.createPath(rangePath + "/offset", 0);
            zooClient.createPath(rangePath + "/max_weight", 0.0);
            zooClient.createPath(rangePath + "/status", UNCOMPLETED.ordinal());
            zooClient.createPath(rangePath + "/lock");
        }
        String uncompletedPath = graphPath + "/dag_forest_max_weight/uncompleted";
        zooClient.createPath(uncompletedPath + "/" + graphId);
        return graphId;
    }

    public double getDAGForestMaxWeightMultiThreaded(DirectedGraph graph) throws InterruptedException {
        return getDAGForestMaxWeightMultiThreaded(graph, 0, 1L << graph.getVertexCount());
    }

    public double getDAGForestMaxWeightSingleThreaded(DirectedGraph graph) {
        return getDAGForestMaxWeightSingleThreaded(graph, 0, 1L << graph.getVertexCount());
    }

    private double getDAGForestMaxWeightMultiThreaded(DirectedGraph graph, long lb, long rb) throws InterruptedException {
        final long rangePerThread = max((rb - lb) / nThreads, 1);
        List<Future<Double>> futures = new ArrayList<>();
        long threadOffset = lb;
        while (threadOffset < rb) {
            final long threadOffsetConst = threadOffset;
            futures.add(graphProcessingExecutor.submit(() -> getDAGForestMaxWeightSingleThreaded(graph, threadOffsetConst, min(rb, threadOffsetConst + rangePerThread))));
            threadOffset += rangePerThread;
        }
        double maxWeight = 0;
        for (Future<Double> future : futures) {
            try {
                double weight = future.get();
                if (weight > maxWeight) {
                    maxWeight = weight;
                }
            } catch (ExecutionException ex) {
                Throwable t = ex.getCause();
                if (t instanceof RuntimeException) {
                    throw (RuntimeException) t;
                } else if (t instanceof Error) {
                    throw (Error) t;
                }
            }
        }
        return maxWeight;
    }

    private double getDAGForestMaxWeightSingleThreaded(DirectedGraph graph, long lb, long rb) {
        double maxWeight = 0;
        for (long mask = lb; mask < rb; mask++) {
            if (hasCycles(graph, mask)) {
                continue;
            }
            double weight = 0;
            for (int i = 0; i < graph.getVertexCount(); i++) {
                if ((mask & (1L << i)) == 0) {
                    continue;
                }
                weight += graph.getVertexWeight(i);
            }
            if (weight > maxWeight) {
                maxWeight = weight;
            }
        }
        return maxWeight;
    }

    private boolean hasCycles(DirectedGraph graph, long mask) {
        int[] marked = new int[graph.getVertexCount()];
        for (int i = 0; i < marked.length; i++) {
            if (marked[i] != 0 || (mask & (1L << i)) == 0) {
                continue;
            }
            if (inCycle(graph, mask, i, marked)) {
                return true;
            }
        }
        return false;
    }

    private boolean inCycle(DirectedGraph graph, long mask, int from, int[] marked) {
        marked[from] = 1;
        Iterator<Edge> edges = graph.getEdgesForVertex(from);
        if (edges != null) {
            while (edges.hasNext()) {
                Edge edge = edges.next();
                int to = edge.getHead();
                if ((mask & (1L << to)) == 0) {
                    continue;
                }
                if (marked[to] == 1) {
                    return true;
                }
                if (marked[to] == 0 && inCycle(graph, mask, to, marked)) {
                    return true;
                }
            }
        }
        marked[from] = 2;
        return false;
    }

    private byte[] toByteArrayMaxWeightForestDAGGraph(DirectedGraph graph) {
        int size = 4 + 8 * graph.getVertexCount() + 4 + 8 * graph.getEdgeCount();
        ByteBuffer buffer = ByteBuffer.wrap(new byte[size]);
        buffer.putInt(graph.getVertexCount());
        for (int i = 0; i < graph.getVertexCount(); i++) {
            buffer.putDouble(graph.getVertexWeight(i));
        }
        buffer.putInt(graph.getEdgeCount());
        for (int i = 0; i < graph.getEdgeCount(); i++) {
            Iterator<Edge> edges = graph.getEdgesForVertex(i);
            if (edges == null) {
                continue;
            }
            while (edges.hasNext()) {
                Edge edge = edges.next();
                buffer.putInt(edge.getTail());
                buffer.putInt(edge.getHead());
            }
        }
        return buffer.array();
    }

    private DirectedGraph fromByteArrayMaxWeightForestDAGGraph(byte[] array) throws IOException {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(array);
            int vertexCount = buffer.getInt();
            DirectedGraph graph = new DirectedGraph(vertexCount);
            for (int i = 0; i < vertexCount; i++) {
                graph.setVertexWeight(i, buffer.getDouble());
            }
            int edgeCount = buffer.getInt();
            for (int i = 0; i < edgeCount; i++) {
                int tail = buffer.getInt();
                int head = buffer.getInt();
                graph.addEdge(new Edge(tail, head));
            }
            return graph;
        } catch (Exception ex) {
            throw new IOException("Bad data in array", ex);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String uncompletedPath = graphPath + "/dag_forest_max_weight/uncompleted";
        zooClient.createOrUpdatePath(uncompletedPath);
        PathChildrenCache cache = new PathChildrenCache(zooClient.getZooClient(), uncompletedPath, true);
        cache.start();
        PathChildrenCacheListener listener = (client, event) -> {
            if (event.getType() == CHILD_ADDED) {
                log.debug("Child-added event received: {}", event);
                nodeProcessingExecutor.submit(new NodesProcessor());
            }
        };
        cache.getListenable().addListener(listener);
    }

    @Override
    public void destroy() {
        log.debug("Destroying GraphService bean...");
        graphProcessingExecutor.shutdownNow();
        nodeProcessingExecutor.shutdownNow();
    }

    private class NodesProcessor implements Runnable {

        @Override
        public void run() {
            try {
                if (!zooClient.blockUntilConnectedOrTimedOut()) {
                    return;
                }
                String uncompletedPath = graphPath + "/dag_forest_max_weight/uncompleted";
                List<String> graphIds = zooClient.getChildZNodes(uncompletedPath);
                if (graphIds == null || graphIds.isEmpty()) {
                    return;
                }
                Collections.shuffle(graphIds);
                log.info("Uncompleted graphs ids: {}", graphIds);
                String calculationsPath = graphPath + "/dag_forest_max_weight/calculations";
                for (String graphId : graphIds) {
                    List<String> ranges = zooClient.getChildZNodes(calculationsPath + "/" + graphId);
                    if (ranges == null || ranges.isEmpty()) {
                        continue;
                    }
                    log.info("Ranges found for graph id = {}: {}", graphId, ranges);
                    int completed = 0;
                    DirectedGraph graph = null;
                    for (String range : ranges) {
                        String statusPath = calculationsPath + "/" + graphId + "/" + range + "/status";
                        if (isStatusCompleted(statusPath)) {
                            completed++;
                            continue;
                        }
                        String lockPath = calculationsPath + "/" + graphId + "/" + range + "/lock";
                        InterProcessSemaphoreMutex lock = zooClient.getLock(lockPath);
                        if (acquireLock(lock, 100, MILLISECONDS)) {
                            try {
                                if (isStatusCompleted(statusPath)) {
                                    completed++;
                                    continue;
                                }
                                if (graph == null) {
                                    String graphDataPath = graphPath + "/dag_forest_max_weight/completed/" + graphId + "/graph_data";
                                    byte[] graphBytes = zooClient.getBytesFromPath(graphDataPath);
                                    try {
                                        graph = fromByteArrayMaxWeightForestDAGGraph(graphBytes);
                                    } catch (IOException ex) {
                                        throw new ZooException(
                                                "Corrupted data for graph with id = " + graphId + ", bytes = " + printHexBinary(graphBytes),
                                                ex);
                                    }
                                }
                                long left = zooClient.getLongFromPath(calculationsPath + "/" + graphId + "/left");
                                long right = zooClient.getLongFromPath(calculationsPath + "/" + graphId + "/right");
                                long offset = zooClient.getLongFromPath(calculationsPath + "/" + graphId + "/offset");
                                double currentWeight = zooClient.getDoubleFromPath(calculationsPath + "/" + graphId + "/max_weight");
                                while (left + offset < right) {
                                    double weight = getDAGForestMaxWeightMultiThreaded(graph,
                                            left + offset,
                                            min(left + offset + OFFSET_READING, right));
                                    if (weight > currentWeight) {
                                        currentWeight = weight;
                                        zooClient.putDoubleToPath(calculationsPath + "/" + graphId + "/max_weight", currentWeight);
                                    }
                                    offset += OFFSET_READING;
                                    zooClient.putLongToPath(calculationsPath + "/" + graphId + "/offset", offset);
                                }
                                completed++;
                                log.info("Processed range = {}, for graph id = {}, max weight = {}", range, graphId, currentWeight);
                            } catch (NoZooNodeException ignored) {
                            } finally {
                                releaseLock(lock);
                            }
                        }
                    }
                }
            } catch (InterruptedException | ZooException ex) {
                log.warn("Exception occurred while processing uncompleted graphs: ", ex);
            }
        }

        private boolean isStatusCompleted(String statusPath) throws ZooException {
            try {
                return zooClient.getLongFromPath(statusPath) == COMPLETED.ordinal();
            } catch (NoZooNodeException ex) {
                return true;
            }
        }
    }
}
