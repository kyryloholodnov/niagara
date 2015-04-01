package com.github.holodnov.graph.service;

import com.github.holodnov.algorithms.graph.DirectedGraph;
import com.github.holodnov.algorithms.graph.Edge;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @author Kyrylo Holodnov
 */
public class GraphService {

    private final int nThreads;
    private final ExecutorService executor;

    public GraphService() {
        this(3 * Runtime.getRuntime().availableProcessors());
    }

    public GraphService(int nThreads) {
        this.nThreads = nThreads;
        executor = newFixedThreadPool(nThreads);
    }

    public double getDAGForestMaxWeightMultiThreaded(DirectedGraph graph) throws InterruptedException {
        return getDAGForestMaxWeightMultiThreaded(graph, 0, 1L << graph.getVertexCount());
    }

    public double getDAGForestMaxWeightSingleThreaded(DirectedGraph graph) {
        return getDAGForestMaxWeightSingleThreaded(graph, 0, 1L << graph.getVertexCount());
    }

    @PreDestroy
    public void destroy() {
        executor.shutdownNow();
    }

    private double getDAGForestMaxWeightMultiThreaded(DirectedGraph graph, long lb, long rb) throws InterruptedException {
        final long rangePerThread = max((rb - lb) / nThreads, 1);
        List<Future<Double>> futures = new ArrayList<>();
        long threadOffset = lb;
        while (threadOffset < rb) {
            final long threadOffsetConst = threadOffset;
            futures.add(executor.submit(() -> getDAGForestMaxWeightSingleThreaded(graph, threadOffsetConst, min(rb, threadOffsetConst + rangePerThread))));
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
}
