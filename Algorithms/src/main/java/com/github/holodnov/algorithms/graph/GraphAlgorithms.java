package com.github.holodnov.algorithms.graph;

import com.github.holodnov.algorithms.unionfind.UnionFind;

import java.util.*;
import java.util.Map.Entry;

import static java.util.AbstractMap.SimpleImmutableEntry;

/**
 * @author Kyrylo Holodnov
 */
public final class GraphAlgorithms {

    private GraphAlgorithms() {
        throw new AssertionError("Cannot invoke constructor for GraphAlgorithms class");
    }

    /**
     * Gets graph clustering with input number of clusters. The minimum distance
     * between all separated vertices (vertices in different clusters) is called
     * "spacing". The goal of this clustering is to maximize "spacing".
     *
     * @param graph input undirected graph
     * @param k     input number of clusters
     * @return ClusteringResult object with maximum "spacing" and clusters
     * inside
     * @throws IllegalArgumentException if input graph is null, or input number
     *                                  of clusters is less than 1, or input number of clusters is greater than
     *                                  number of vertices
     */
    public static ClusteringResult getClusteringComponents(UndirectedGraph graph,
                                                           int k) {
        if (graph == null) {
            throw new IllegalArgumentException("Input graph should be not null");
        }
        if (k <= 1) {
            throw new IllegalArgumentException("Input number of components "
                    + "after clusterization "
                    + "should be greater than 1");
        }
        if (k > graph.getVertexCount()) {
            throw new IllegalArgumentException("Input number of components "
                    + "after clusterization "
                    + "should not be greater than number of vertices");
        }
        Set<Entry<Integer, Integer>> unique = new HashSet<>();
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < graph.getVertexCount(); i++) {
            Iterator<Edge> edgeIterator = graph.getEdgesForVertex(i);
            if (edgeIterator == null) {
                continue;
            }
            while (edgeIterator.hasNext()) {
                Edge edge = edgeIterator.next();
                Entry<Integer, Integer> mutableEntry
                        = new SimpleImmutableEntry<>(i, edge.getHead());
                if (!unique.contains(mutableEntry)) {
                    mutableEntry = new SimpleImmutableEntry<>(edge.getHead(), i);
                    if (unique.add(mutableEntry)) {
                        edges.add(new Edge(i, edge.getHead(), edge.getWeight()));
                    }
                }
            }
        }
        Collections.sort(edges,
                (Edge o1, Edge o2) -> o1.getWeight() > o2.getWeight() ? 1 : o1.getWeight() < o2.getWeight() ? -1 : 0);
        UnionFind unionFind = new UnionFind(graph.getVertexCount());
        double maxSpacing = 0;
        int maxSpacingFirstVertex = -1;
        int maxSpacingSecondVertex = -1;
        for (Edge edge : edges) {
            int c1 = unionFind.find(edge.getTail());
            int c2 = unionFind.find(edge.getHead());
            if (c1 != c2 && unionFind.getSubsetsCount() == k) {
                maxSpacing = edge.getWeight();
                maxSpacingFirstVertex = Math.min(edge.getTail(), edge.getHead());
                maxSpacingSecondVertex = Math.max(edge.getTail(), edge.getHead());
                break;
            } else if (c1 != c2) {
                unionFind.union(edge.getTail(), edge.getHead());
            }
        }
        Map<Integer, Set<Integer>> cluster2vertices = new LinkedHashMap<>();
        for (int i = 0; i < graph.getVertexCount(); i++) {
            int cluster = unionFind.find(i);
            Set<Integer> set = cluster2vertices.get(cluster);
            if (set == null) {
                set = new HashSet<>();
                cluster2vertices.put(cluster, set);
            }
            set.add(i);
        }
        Set<Integer>[] clusters = new HashSet[k];
        int i = 0;
        for (Entry<Integer, Set<Integer>> entry : cluster2vertices.entrySet()) {
            clusters[i++] = entry.getValue();
        }
        return new ClusteringResult(clusters,
                maxSpacing,
                maxSpacingFirstVertex,
                maxSpacingSecondVertex);
    }

    /**
     * Gets minimum spanning tree (or forest if graph has more than 1 connected components) for given undirected graph.
     *
     * @param graph input undirected graph
     * @return tree or forest with minimum weight
     * @see <a
     * href="http://en.wikipedia.org/wiki/Kruskal%27s_algorithm">http://en.wikipedia.org/wiki/Kruskal%27s_algorithm</a>
     */
    public static UndirectedGraph getMinimumSpanningTree(UndirectedGraph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Input graph should be not null");
        }
        List<Edge> edges = new ArrayList<>(graph.getEdgeCount());
        for (int i = 0; i < graph.getVertexCount(); i++) {
            Iterator<Edge> edgesIterator = graph.getEdgesForVertex(i);
            while (edgesIterator.hasNext()) {
                Edge edge = edgesIterator.next();
                if (edge.getHead() < i) {
                    edges.add(edge);
                }
            }
        }
        Collections.sort(edges,
                (Edge e1, Edge e2) -> e1.getWeight() > e2.getWeight() ? 1 : e1.getWeight() < e2.getWeight() ? -1 : 0);
        UnionFind unionFind = new UnionFind(graph.getVertexCount());
        UndirectedGraph minimumSpanningTree = new UndirectedGraph(graph.getVertexCount());
        for (Edge edge : edges) {
            if (unionFind.find(edge.getTail()) == unionFind.find(edge.getHead())) {
                continue;
            }
            unionFind.union(edge.getTail(), edge.getHead());
            minimumSpanningTree.addEdge(edge);
        }
        return minimumSpanningTree;
    }
}
