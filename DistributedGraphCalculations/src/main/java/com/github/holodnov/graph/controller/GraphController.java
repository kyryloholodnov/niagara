package com.github.holodnov.graph.controller;

import com.github.holodnov.algorithms.graph.DirectedGraph;
import com.github.holodnov.graph.exception.UnparseableGraphDataException;
import com.github.holodnov.graph.http.Response;
import com.github.holodnov.graph.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.github.holodnov.graph.http.RequestUtils.appendEdges;
import static com.github.holodnov.graph.http.RequestUtils.getGraphWithVertices;

/**
 * @author Kyrylo Holodnov
 */
@Controller
public class GraphController {

    @Autowired
    private GraphService graphService;

    @RequestMapping(value = "/dag_forest_max_weight_single_thread",
            method = RequestMethod.GET)
    @ResponseBody
    public Response getDAGForestMaxWeightSingleThread(@RequestParam String vertices,
                                                      @RequestParam(required = false) String edges) throws UnparseableGraphDataException {
        long start = System.currentTimeMillis();
        DirectedGraph graph = (DirectedGraph) getGraphWithVertices(vertices, true);
        appendEdges(graph, edges);
        double maxWeight = graphService.getDAGForestMaxWeightSingleThreaded(graph);
        return new Response().
                setGraph(graph).
                setMaxWeight(maxWeight).
                setElapsed(System.currentTimeMillis() - start);
    }

    @RequestMapping(value = "/dag_forest_max_weight_multi_threads",
            method = RequestMethod.GET)
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
                setMaxWeight(maxWeight).
                setElapsed(System.currentTimeMillis() - start);
    }
}
