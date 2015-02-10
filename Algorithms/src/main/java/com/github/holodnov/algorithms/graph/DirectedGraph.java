package com.github.holodnov.algorithms.graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A <tt>DirectedGraph</tt> is an implementation for directed graphs.
 *
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://en.wikipedia.org/wiki/Graph_(mathematics)#Directed_graph">http://en.wikipedia.org/wiki/Graph_(mathematics)#Directed_graph</a>
 */
public class DirectedGraph implements Graph, Serializable {

    static final long serialVersionUID = 201502100253L;

    private double[] vertices;
    private final List<Edge>[] edges;
    private final int vertexCount;
    protected int edgeCount;

    public DirectedGraph(int vertexCount) {
        if (vertexCount <= 0) {
            throw new IllegalArgumentException("Vertex count should be positive");
        }
        this.vertexCount = vertexCount;
        this.edges = new List[vertexCount];
    }

    @Override
    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public int getEdgeCount() {
        return edgeCount;
    }

    @Override
    public void setVertexWeight(int vertex, double weight) {
        checkVertexRange(vertex);
        if (vertices == null) {
            vertices = new double[vertexCount];
        }
        vertices[vertex] = weight;
    }

    @Override
    public double getVertexWeight(int vertex) {
        checkVertexRange(vertex);
        if (vertices == null) {
            vertices = new double[vertexCount];
        }
        return vertices[vertex];
    }

    @Override
    public Iterator<Edge> getEdgesForVertex(int vertex) {
        checkVertexRange(vertex);
        List<Edge> edgesForVertex = edges[vertex];
        if (edgesForVertex == null) {
            return null;
        }
        return new EdgesForVertexIterator(edgesForVertex);
    }

    @Override
    public void addEdge(Edge edge) {
        if (edge == null) {
            throw new IllegalArgumentException("Input edge should be not null");
        }
        checkEdgeRange(edge.getTail(), edge.getHead());
        if (edges[edge.getTail()] == null) {
            edges[edge.getTail()] = new ArrayList<>(1);
        }
        edges[edge.getTail()].add(edge);
        edgeCount++;
    }

    private void checkVertexRange(int vertex) {
        if (vertex < 0 || vertex >= vertexCount) {
            throw new IllegalArgumentException("Vertex should be between "
                    + "0 (inclusive) and " + vertexCount + " (exclusive)");
        }
    }

    private void checkEdgeRange(int from, int to) {
        if (from < 0 || from >= vertexCount) {
            throw new IllegalArgumentException("From-vertex should be between "
                    + "0 (inclusive) and " + vertexCount + " (exclusive)");
        }
        if (to < 0 || to >= vertexCount) {
            throw new IllegalArgumentException("To-vertex should be between "
                    + "0 (inclusive) and " + vertexCount + " (exclusive)");
        }
    }

    private static class EdgesForVertexIterator implements Iterator<Edge> {

        private final Iterator<Edge> iterator;

        public EdgesForVertexIterator(List<Edge> edges) {
            this.iterator = edges.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Edge next() {
            return iterator.next();
        }
    }
}
