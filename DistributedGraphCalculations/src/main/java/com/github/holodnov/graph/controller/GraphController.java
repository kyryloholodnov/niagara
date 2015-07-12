package com.github.holodnov.graph.controller;

import com.github.holodnov.algorithms.graph.DirectedGraph;
import com.github.holodnov.graph.exception.UnparseableGraphDataException;
import com.github.holodnov.graph.http.Response;
import com.github.holodnov.graph.service.GraphService;
import com.github.holodnov.graph.zoo.ZooException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.github.holodnov.graph.http.RequestUtils.appendEdges;
import static com.github.holodnov.graph.http.RequestUtils.getGraphWithVertices;
import static com.github.holodnov.graph.service.Status.COMPLETED;
import static com.github.holodnov.graph.service.Status.UNCOMPLETED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author Kyrylo Holodnov
 */
@RestController
public class GraphController {

    @Autowired
    private GraphService graphService;

    @RequestMapping(value = "/dag_forest_max_weight_single_thread",
            method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Response getDAGForestMaxWeightSingleThread(@RequestParam String vertices,
                                                      @RequestParam(required = false) String edges)
            throws UnparseableGraphDataException {
        long start = System.currentTimeMillis();
        DirectedGraph graph = (DirectedGraph) getGraphWithVertices(vertices, true);
        appendEdges(graph, edges);
        double maxWeight = graphService.getDAGForestMaxWeightSingleThreaded(graph);
        return new Response().
                setGraph(graph).
                setStatus(COMPLETED).
                setMaxWeight(maxWeight).
                setWorkTime(System.currentTimeMillis() - start);
    }

    @RequestMapping(value = "/dag_forest_max_weight_multi_threads",
            method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Response getDAGForestMaxWeightMultiThread(@RequestParam String vertices,
                                                     @RequestParam(required = false) String edges)
            throws UnparseableGraphDataException, InterruptedException {
        long start = System.currentTimeMillis();
        DirectedGraph graph = (DirectedGraph) getGraphWithVertices(vertices, true);
        appendEdges(graph, edges);
        double maxWeight = graphService.getDAGForestMaxWeightMultiThreaded(graph);
        return new Response().
                setGraph(graph).
                setStatus(COMPLETED).
                setMaxWeight(maxWeight).
                setWorkTime(System.currentTimeMillis() - start);
    }

    @RequestMapping(value = "/dag_forest_max_weight_distributed",
            method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Response sendDAGForestMaxWeighDistributed(@RequestParam String vertices,
                                                     @RequestParam(required = false) String edges)
            throws Exception {
        long start = System.currentTimeMillis();
        DirectedGraph graph = (DirectedGraph) getGraphWithVertices(vertices, true);
        appendEdges(graph, edges);
        String graphId = graphService.sendDAGForestMaxWeightDistributed(graph);
        return new Response().
                setGraphId(graphId).
                setGraph(graph).
                setStatus(UNCOMPLETED).
                setWorkTime(System.currentTimeMillis() - start);
    }

    @RequestMapping(value = "/dag_forest_max_weight_distributed_result",
            method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Response getDAGForestMaxWeighDistributedResult(@RequestParam String graphId)
            throws ZooException, IOException {
        return graphService.getDAGForestMaxWeighDistributedResult(graphId);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler
    private Response handleUnparseableGraphDataException(UnparseableGraphDataException e, HttpServletResponse response) {
        return new Response().setError(e.getMessage());
    }
}
