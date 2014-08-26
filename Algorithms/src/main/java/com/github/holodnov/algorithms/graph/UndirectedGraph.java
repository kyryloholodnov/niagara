package com.github.holodnov.algorithms.graph;

/**
 * A <tt>UndirectedGraph</tt> is an implementation for undirected graphs.
 *
 * @see <a
 * href="http://en.wikipedia.org/wiki/Graph_(mathematics)#Undirected_graph">http://en.wikipedia.org/wiki/Graph_(mathematics)#Undirected_graph</a>
 * @author Kyrylo Holodnov
 */
public class UndirectedGraph extends DirectedGraph {

    public UndirectedGraph(int vertexCount) {
        super(vertexCount);
    }

    @Override
    public void addEdge(int from, int to, double weight) {
        super.addEdge(from, to, weight);
        super.addEdge(to, from, weight);
        edgeCount--;
    }

    @Override
    public void addEdge(int from, int to) {
        super.addEdge(from, to);
        super.addEdge(to, from);
        edgeCount--;
    }
}
