package com.github.holodnov.graph.controller;

import com.github.holodnov.algorithms.graph.DirectedGraph;
import com.github.holodnov.algorithms.graph.UndirectedGraph;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Kyrylo Holodnov
 */
@Controller
public class GraphController {

    @RequestMapping(value = "/max_weight_dag", method = RequestMethod.GET)
    @ResponseBody
    public DirectedGraph getMaxWeightDAG() {
        // TODO this is stub: put code here
        UndirectedGraph graph = new UndirectedGraph(5);
        graph.addEdge(0, 1, 10.0);
        graph.addEdge(1, 2, 11.0);
        graph.addEdge(2, 3, 13.0);
        graph.addEdge(3, 4, 14.0);
        graph.addEdge(4, 0, 15.0);
        return graph;
    }
}
