package com.github.holodnov.graph.service;

import com.github.holodnov.algorithms.graph.DirectedGraph;
import com.github.holodnov.algorithms.graph.Edge;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 */
public class GraphServiceTest {

    @Test
    public void testGetDAGForestMaxWeightMultiThreaded1() throws Exception {
        GraphService graphService = new GraphService();
        DirectedGraph graph = new DirectedGraph(2);
        graph.setVertexWeight(0, 10);
        graph.setVertexWeight(1, 20);
        graph.addEdge(new Edge(0, 1));
        graph.addEdge(new Edge(1, 0));
        assertThat("Two-vertex cycled graph: ", graphService.getDAGForestMaxWeightMultiThreaded(graph), is(20.0));
    }

    @Test
    public void testGetDAGForestMaxWeightMultiThreaded2() throws Exception {
        GraphService graphService = new GraphService();
        DirectedGraph graph = new DirectedGraph(2);
        graph.setVertexWeight(0, 10);
        graph.setVertexWeight(1, 20);
        graph.addEdge(new Edge(0, 1));
        assertThat("Two-vertex graph: ", graphService.getDAGForestMaxWeightMultiThreaded(graph), is(30.0));
    }

    @Test
    public void testGetDAGForestMaxWeightMultiThreaded3() throws Exception {
        GraphService graphService = new GraphService();
        DirectedGraph graph = new DirectedGraph(3);
        graph.setVertexWeight(0, 10);
        graph.setVertexWeight(1, 20);
        graph.setVertexWeight(2, 15);
        graph.addEdge(new Edge(0, 1));
        graph.addEdge(new Edge(1, 0));
        assertThat("Three-vertex graph with isolated vertex: ",
                graphService.getDAGForestMaxWeightMultiThreaded(graph), is(35.0));
    }

    @Test
    public void testGetDAGForestMaxWeightMultiThreaded4() throws Exception {
        GraphService graphService = new GraphService();
        DirectedGraph graph = new DirectedGraph(3);
        graph.setVertexWeight(0, 10);
        graph.setVertexWeight(1, 20);
        graph.setVertexWeight(2, 15);
        graph.addEdge(new Edge(0, 2));
        graph.addEdge(new Edge(1, 2));
        assertThat("Three-vertex graph all edges to one vertex: ",
                graphService.getDAGForestMaxWeightMultiThreaded(graph), is(45.0));
    }
}
