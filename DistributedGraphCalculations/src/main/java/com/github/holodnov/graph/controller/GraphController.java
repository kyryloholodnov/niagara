package com.github.holodnov.graph.controller;

import com.github.holodnov.algorithms.graph.DirectedGraph;
import com.github.holodnov.graph.exception.UnparseableGraphDataException;
import com.github.holodnov.graph.http.Response;
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

    @RequestMapping(value = "/max_weight",
            method = RequestMethod.GET)
    @ResponseBody
    public Response getMaxWeight(@RequestParam String vertices,
                                 @RequestParam(required = false) String edges) throws UnparseableGraphDataException {
        DirectedGraph graph = (DirectedGraph) getGraphWithVertices(vertices, true);
        appendEdges(graph, edges);
        return new Response().setGraph(graph);
    }
}
