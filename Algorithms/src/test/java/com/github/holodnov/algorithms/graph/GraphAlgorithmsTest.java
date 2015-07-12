package com.github.holodnov.algorithms.graph;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import static com.github.holodnov.algorithms.graph.GraphAlgorithms.getClusteringComponents;
import static com.github.holodnov.algorithms.graph.GraphAlgorithms.getMinimumSpanningTree;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Kyrylo Holodnov
 */
public class GraphAlgorithmsTest {

    private static final double EPSILON = 0.0000001;

    @Test(expected = IllegalArgumentException.class)
    public void testGetClusteringComponentsNullGraph() {
        getClusteringComponents(null, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetClusteringComponentsNegativeK() {
        UndirectedGraph graph = new UndirectedGraph(3);
        getClusteringComponents(graph, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetClusteringComponentsKIsZero() {
        UndirectedGraph graph = new UndirectedGraph(3);
        getClusteringComponents(graph, 0);
    }

    @Test
    public void testGetClusteringComponents1() {
        UndirectedGraph graph = new UndirectedGraph(4);
        graph.addEdge(new Edge(0, 1, 1));
        graph.addEdge(new Edge(1, 2, 2));
        graph.addEdge(new Edge(2, 3, 3));
        graph.addEdge(new Edge(3, 0, 4));
        int k = 2;
        ClusteringResult result = getClusteringComponents(graph, k);
        // Expected clusters: {0, 1, 2}, {3}
        assertArrayEquals(new HashSet[]{
                new HashSet<>(Arrays.asList(0, 1, 2)),
                new HashSet<>(singletonList(3))
        }, result.getClusters());
        assertEquals(3, result.getMaxSpacing(), EPSILON);
        assertEquals(2, result.getMaxSpacingFirstVertex());
        assertEquals(3, result.getMaxSpacingSecondVertex());
    }

    @Test
    public void testGetClusteringComponents2() {
        UndirectedGraph graph = new UndirectedGraph(4);
        graph.addEdge(new Edge(0, 1, 1));
        graph.addEdge(new Edge(1, 2, 2));
        graph.addEdge(new Edge(2, 3, 3));
        graph.addEdge(new Edge(3, 0, 4));
        int k = 3;
        ClusteringResult result = getClusteringComponents(graph, k);
        // Expected clusters: {0, 1}, {2}, {3}
        assertArrayEquals(new HashSet[]{
                new HashSet<>(Arrays.asList(0, 1)),
                new HashSet<>(singletonList(2)),
                new HashSet<>(singletonList(3))
        }, result.getClusters());
        assertEquals(2, result.getMaxSpacing(), EPSILON);
        assertEquals(1, result.getMaxSpacingFirstVertex());
        assertEquals(2, result.getMaxSpacingSecondVertex());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMinimumSpanningTreeNullGraph() {
        getMinimumSpanningTree(null);
    }

    @Test
    public void testGetMinimumSpanningTree1() {
        UndirectedGraph graph = new UndirectedGraph(3);
        graph.addEdge(new Edge(0, 1, 1));
        graph.addEdge(new Edge(1, 2, 1));
        graph.addEdge(new Edge(2, 0, 1));
        UndirectedGraph minimumSpanningTree = GraphAlgorithms.getMinimumSpanningTree(graph);
        assertEquals(3, minimumSpanningTree.getVertexCount());
        assertEquals(2, minimumSpanningTree.getEdgeCount());
        double weight = 0;
        for (int i = 0; i < minimumSpanningTree.getVertexCount(); i++) {
            Iterator<Edge> edgesIterator = minimumSpanningTree.getEdgesForVertex(i);
            while (edgesIterator.hasNext()) {
                Edge edge = edgesIterator.next();
                if (edge.getHead() < i) {
                    weight += edge.getWeight();
                }
            }
        }
        assertEquals(2, weight, EPSILON);
    }

    @Test
    public void testGetMinimumSpanningTree2() {
        UndirectedGraph graph = new UndirectedGraph(3);
        graph.addEdge(new Edge(0, 1, 1));
        graph.addEdge(new Edge(1, 2, 3));
        graph.addEdge(new Edge(2, 0, 3));
        UndirectedGraph minimumSpanningTree = GraphAlgorithms.getMinimumSpanningTree(graph);
        assertEquals(3, minimumSpanningTree.getVertexCount());
        assertEquals(2, minimumSpanningTree.getEdgeCount());
        double weight = 0;
        for (int i = 0; i < minimumSpanningTree.getVertexCount(); i++) {
            Iterator<Edge> edgesIterator = minimumSpanningTree.getEdgesForVertex(i);
            while (edgesIterator.hasNext()) {
                Edge edge = edgesIterator.next();
                if (edge.getHead() < i) {
                    weight += edge.getWeight();
                }
            }
        }
        assertEquals(4, weight, EPSILON);
    }
}
