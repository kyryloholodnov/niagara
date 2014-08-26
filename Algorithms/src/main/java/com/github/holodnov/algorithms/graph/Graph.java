package com.github.holodnov.algorithms.graph;

import java.util.Iterator;
import java.util.Map.Entry;

/**
 * @author Kyrylo Holodnov
 */
public interface Graph {

    int getVertexCount();

    int getEdgeCount();

    void setVertexWeight(int vertex, double weight);

    double getVertexWeight(int vertex);

    Iterator<Entry<Integer, Double>> getEdgesForVertex(int vertex);

    void addEdge(int from, int to, double weight);

    default void addEdge(int from, int to) {
        addEdge(from, to, 1.0);
    }
}
