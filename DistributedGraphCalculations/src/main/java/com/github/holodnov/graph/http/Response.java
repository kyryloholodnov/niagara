package com.github.holodnov.graph.http;

import com.github.holodnov.algorithms.graph.Graph;
import com.github.holodnov.graph.service.Status;

/**
 * @author Kyrylo Holodnov
 */
public class Response {

    private String graphId;
    private Graph graph;
    private Status status;
    private Double maxWeight;
    private Long workTime;
    private String error;

    public Response() {
    }

    public String getGraphId() {
        return graphId;
    }

    public Response setGraphId(String graphId) {
        this.graphId = graphId;
        return this;
    }

    public Graph getGraph() {
        return graph;
    }

    public Response setGraph(Graph graph) {
        this.graph = graph;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Response setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Double getMaxWeight() {
        return maxWeight;
    }

    public Response setMaxWeight(Double maxWeight) {
        this.maxWeight = maxWeight;
        return this;
    }

    public Long getWorkTime() {
        return workTime;
    }

    public Response setWorkTime(Long workTime) {
        this.workTime = workTime;
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
