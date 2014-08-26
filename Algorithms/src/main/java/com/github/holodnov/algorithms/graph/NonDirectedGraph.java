package com.github.holodnov.algorithms.graph;

/**
 * @author Kyrylo Holodnov
 */
public class NonDirectedGraph extends DirectedGraph {

    public NonDirectedGraph(int vertexCount) {
        super(vertexCount);
    }

    @Override
    public void addEdge(int from, int to, double weight) {
        super.addEdge(from, to, weight);
        super.addEdge(to, from, weight);
        edgeCount--;
    }
}
