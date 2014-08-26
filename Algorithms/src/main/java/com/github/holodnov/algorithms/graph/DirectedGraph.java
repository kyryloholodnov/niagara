package com.github.holodnov.algorithms.graph;

import java.util.ArrayList;
import java.util.List;
import static java.util.AbstractMap.Entry;
import static java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Iterator;

/**
 * @author Kyrylo Holodnov
 */
public class DirectedGraph implements Graph {

    private static final Double ONE = 1.0;

    private double[] vertices;
    private final List<Entry<Integer, Double>>[] edges;
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
    public Iterator<Entry<Integer, Double>> getEdgesForVertex(int vertex) {
        checkVertexRange(vertex);
        List<Entry<Integer, Double>> edgesForVertex = edges[vertex];
        if (edgesForVertex == null) {
            return null;
        }
        return new EdgesForVertexIterator(edgesForVertex);
    }

    @Override
    public void addEdge(int from, int to, double weight) {
        checkEdgeRange(from, to);
        if (edges[from] == null) {
            edges[from] = new ArrayList<>(1);
        }
        edges[from].add(new SimpleImmutableEntry<>(to, weight));
        edgeCount++;
    }

    @Override
    public void addEdge(int from, int to) {
        checkEdgeRange(from, to);
        if (edges[from] == null) {
            edges[from] = new ArrayList<>(1);
        }
        edges[from].add(new SimpleImmutableEntry<>(to, ONE));
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

    private static class EdgesForVertexIterator implements Iterator<Entry<Integer, Double>> {

        private final Iterator<Entry<Integer, Double>> iterator;

        public EdgesForVertexIterator(List<Entry<Integer, Double>> edges) {
            this.iterator = edges.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Entry<Integer, Double> next() {
            return iterator.next();
        }
    }
}
