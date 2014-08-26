package com.github.holodnov.algorithms.graph;

import java.util.Iterator;
import java.util.Map.Entry;

/**
 * A <tt>Graph</tt> is an interface for graph algorithms.
 *
 * @see <a
 * href="http://en.wikipedia.org/wiki/Graph_(mathematics)">http://en.wikipedia.org/wiki/Graph_(mathematics)</a>
 * @author Kyrylo Holodnov
 */
public interface Graph {

    /**
     * Retrieves the number of vertices <b>|V|</b> in this graph.
     *
     * @return number of vertices in this graph
     */
    int getVertexCount();

    /**
     * Retrieves the number of edges <b>|E|</b> in this graph.
     *
     * @return number of vertices in this graph
     */
    int getEdgeCount();

    /**
     * Sets vertex weight for input vertex (some algorithms require vertices
     * with their weights).
     *
     * @param vertex input vertex
     * @param weight input weight
     * @throws IllegalArgumentException if vertex out of bounds
     */
    void setVertexWeight(int vertex, double weight);

    /**
     * Retrieves vertex weight for input vertex (some algorithms require
     * vertices with their weights).
     *
     * @param vertex input vertex
     * @return vertex weight
     * @throws IllegalArgumentException if vertex out of bounds
     */
    double getVertexWeight(int vertex);

    /**
     * Retrieves edges for input vertex (this vertex is a tail of edges).
     *
     * @param vertex input vertex
     * @return iterator of entries, each entry is "head-to-weight" pair
     * @throws IllegalArgumentException if vertex out of bounds
     */
    Iterator<Entry<Integer, Double>> getEdgesForVertex(int vertex);

    /**
     * Adds new edge to graph.
     *
     * @param from from-vertex (edge tail)
     * @param to to-vertex (edge head)
     * @param weight edge weight
     * @throws IllegalArgumentException if vertices out of bounds
     */
    void addEdge(int from, int to, double weight);

    /**
     * Adds new edge with default weight (1.0) to graph.
     *
     * @param from from-vertex (edge tail)
     * @param to to-vertex (edge head)
     * @throws IllegalArgumentException if vertices out of bounds
     */
    default void addEdge(int from, int to) {
        addEdge(from, to, 1.0);
    }
}
