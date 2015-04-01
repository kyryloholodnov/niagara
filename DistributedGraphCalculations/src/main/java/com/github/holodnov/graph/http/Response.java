package com.github.holodnov.graph.http;

import com.github.holodnov.algorithms.graph.Graph;

/**
 * @author Kyrylo Holodnov
 */
public class Response {

    private Graph graph;
    private Double maxWeight;
    private Long elapsed;
    private String error;

    public Response() {
    }

    public Graph getGraph() {
        return graph;
    }

    public Response setGraph(Graph graph) {
        this.graph = graph;
        return this;
    }

    public Double getMaxWeight() {
        return maxWeight;
    }

    public Response setMaxWeight(Double maxWeight) {
        this.maxWeight = maxWeight;
        return this;
    }

    public Long getElapsed() {
        return elapsed;
    }

    public Response setElapsed(Long elapsed) {
        this.elapsed = elapsed;
        return this;
    }

    public String getError() {
        return error;
    }

    public Response setError(String error) {
        this.error = error;
        return this;
    }
}
