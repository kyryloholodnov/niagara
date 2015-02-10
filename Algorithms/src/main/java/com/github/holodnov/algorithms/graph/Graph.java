package com.github.holodnov.algorithms.graph;

import java.util.Iterator;

/**
 * A <tt>Graph</tt> is an interface for graph algorithms.
 *
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://en.wikipedia.org/wiki/Graph_(mathematics)">http://en.wikipedia.org/wiki/Graph_(mathematics)</a>
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
     * @return iterator of entries
     * @throws IllegalArgumentException if vertex out of bounds
     */
    Iterator<Edge> getEdgesForVertex(int vertex);

    /**
     * Adds new edge to graph.
     *
     * @param edge input edge
     * @throws IllegalArgumentException if edge is null or indices are out-of-bound
     */
    void addEdge(Edge edge);
}
