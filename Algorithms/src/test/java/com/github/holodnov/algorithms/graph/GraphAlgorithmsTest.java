package com.github.holodnov.algorithms.graph;

import static com.github.holodnov.algorithms.graph.GraphAlgorithms.getClusteringComponents;
import java.util.Arrays;
import java.util.HashSet;
import static org.junit.Assert.*;
import org.junit.Test;

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
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 3);
        graph.addEdge(3, 0, 4);
        int k = 2;
        ClusteringResult result = getClusteringComponents(graph, k);
        // Expected clusters: {0, 1, 2}, {3}
        assertArrayEquals(new HashSet[]{
            new HashSet<>(Arrays.asList(0, 1, 2)),
            new HashSet<>(Arrays.asList(3))
        }, result.getClusters());
        assertEquals(3, result.getMaxSpacing(), EPSILON);
        assertEquals(2, result.getMaxSpacingFirstVertex());
        assertEquals(3, result.getMaxSpacingSecondVertex());
    }

    @Test
    public void testGetClusteringComponents2() {
        UndirectedGraph graph = new UndirectedGraph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 3);
        graph.addEdge(3, 0, 4);
        int k = 3;
        ClusteringResult result = getClusteringComponents(graph, k);
        // Expected clusters: {0, 1}, {2}, {3}
        assertArrayEquals(new HashSet[]{
            new HashSet<>(Arrays.asList(0, 1)),
            new HashSet<>(Arrays.asList(2)),
            new HashSet<>(Arrays.asList(3))
        }, result.getClusters());
        assertEquals(2, result.getMaxSpacing(), EPSILON);
        assertEquals(1, result.getMaxSpacingFirstVertex());
        assertEquals(2, result.getMaxSpacingSecondVertex());
    }
}
