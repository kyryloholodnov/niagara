package com.github.holodnov.algorithms.graph;

import java.io.Serializable;

/**
 * A <tt>UndirectedGraph</tt> is an implementation for undirected graphs.
 *
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://en.wikipedia.org/wiki/Graph_(mathematics)#Undirected_graph">http://en.wikipedia.org/wiki/Graph_(mathematics)#Undirected_graph</a>
 */
public class UndirectedGraph extends DirectedGraph implements Serializable {

    static final long serialVersionUID = 201502100252L;

    public UndirectedGraph(int vertexCount) {
        super(vertexCount);
    }

    @Override
    public void addEdge(Edge edge) {
        super.addEdge(edge);
        super.addEdge(edge.reverse());
        edgeCount--;
    }
}
