package com.github.holodnov.algorithms.graph;

import java.io.Serializable;
import java.util.Arrays;

/**
 * A <tt>UndirectedGraph</tt> is an implementation for undirected graphs.
 *
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://en.wikipedia.org/wiki/Graph_(mathematics)#Undirected_graph">http://en.wikipedia.org/wiki/Graph_(mathematics)#Undirected_graph</a>
 */
public class UndirectedGraph extends DirectedGraph implements Serializable {

    private static final long serialVersionUID = 201503092110L;

    protected UndirectedGraph() {
        super();
    }

    public UndirectedGraph(int vertexCount) {
        super(vertexCount);
    }

    @Override
    public void addEdge(Edge edge) {
        super.addEdge(edge);
        super.addEdge(edge.reverse());
        edgeCount--;
    }

    @Override
    public String toString() {
        return "UndirectedGraph{" +
                "vertices=" + Arrays.toString(vertices) +
                ", edges=" + Arrays.toString(edges) +
                ", vertexCount=" + vertexCount +
                ", edgeCount=" + edgeCount +
                '}';
    }
}
