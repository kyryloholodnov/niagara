package com.github.holodnov.graph.controller;

import com.github.holodnov.algorithms.graph.DirectedGraph;
import com.github.holodnov.algorithms.graph.UndirectedGraph;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Kyrylo Holodnov
 */
@Controller
public class GraphController {

    @RequestMapping(value = "/max_weight_dag",
            method = RequestMethod.POST,
            headers = "content-type=application/json,application/xml")
    @ResponseBody
    public DirectedGraph getMaxWeightDAG(@RequestBody UndirectedGraph graph) {
        // TODO this is stub: put code here
        return graph;
    }
}
