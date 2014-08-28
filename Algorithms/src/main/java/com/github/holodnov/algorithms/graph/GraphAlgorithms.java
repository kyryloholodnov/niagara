package com.github.holodnov.algorithms.graph;

import com.github.holodnov.algorithms.unionfind.UnionFind;
import static java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Kyrylo Holodnov
 */
public final class GraphAlgorithms {

    private GraphAlgorithms() {
    }

    /**
     * Gets graph clustering with input number of clusters. The minimum distance
     * between all separated vertices (vertices in different clusters) is called
     * "spacing". The goal of this clustering is to maximize "spacing".
     *
     * @param graph input undirected graph
     * @param k input number of clusters
     * @return ClusteringResult object with maximum "spacing" and clusters
     * inside
     * @throws IllegalArgumentException if input graph is null, or input number
     * of clusters is less than 1, or input number of clusters is greater than
     * number of vertices
     */
    public static ClusteringResult getClusteringComponents(
            UndirectedGraph graph,
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
            Iterator<Entry<Integer, Double>> edgeIterator = graph.getEdgesForVertex(i);
            if (edgeIterator == null) {
                continue;
            }
            while (edgeIterator.hasNext()) {
                Entry<Integer, Double> entry = edgeIterator.next();
                Entry<Integer, Integer> mutableEntry
                        = new SimpleImmutableEntry<>(i, entry.getKey());
                if (!unique.contains(mutableEntry)) {
                    mutableEntry = new SimpleImmutableEntry<>(entry.getKey(), i);
                    if (unique.add(mutableEntry)) {
                        edges.add(new Edge(i, entry.getKey(), entry.getValue()));
                    }
                }
            }
        }
        Collections.sort(edges,
                (Edge o1, Edge o2) -> o1.weight > o2.weight ? 1 : o1.weight < o2.weight ? -1 : 0);
        UnionFind unionFind = new UnionFind(graph.getVertexCount());
        double maxSpacing = 0;
        for (Edge edge : edges) {
            int c1 = unionFind.find(edge.from);
            int c2 = unionFind.find(edge.to);
            if (c1 != c2 && unionFind.getSubsetsCount() == k) {
                maxSpacing = edge.weight;
                break;
            } else if (c1 != c2) {
                unionFind.union(edge.from, edge.to);
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
        return new ClusteringResult(clusters, maxSpacing);
    }

    private static class Edge {

        final int from;
        final int to;
        final double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
