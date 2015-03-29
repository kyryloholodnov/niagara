package com.github.holodnov.graph.http;

import com.github.holodnov.algorithms.graph.DirectedGraph;
import com.github.holodnov.algorithms.graph.Edge;
import com.github.holodnov.algorithms.graph.Graph;
import com.github.holodnov.algorithms.graph.UndirectedGraph;
import com.github.holodnov.graph.exception.UnparseableGraphDataException;

import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.trimToNull;

/**
 * @author Kyrylo Holodnov
 */
public class RequestUtils {

    private static final Pattern COMMA = Pattern.compile(",");

    private RequestUtils() {
        throw new AssertionError("Cannot create RequestUtils object");
    }

    public static Graph getGraphWithVertices(String vertices, boolean directed) throws UnparseableGraphDataException {
        if (vertices == null) {
            throw new UnparseableGraphDataException("Input vertices should be not null");
        }
        String[] parsed = COMMA.split(vertices);
        Graph graph = directed ? new DirectedGraph(parsed.length) : new UndirectedGraph(parsed.length);
        for (int i = 0; i < parsed.length; i++) {
            try {
                graph.setVertexWeight(i, parseDouble(parsed[i].trim()));
            } catch (NumberFormatException ex) {
                throw new UnparseableGraphDataException("Cannot parse token as double value: " + parsed[i]);
            }
        }
        return graph;
    }

    public static Graph appendEdges(Graph graph, String edges) throws UnparseableGraphDataException {
        edges = trimToNull(edges);
        if (edges == null) {
            return graph;
        }
        while (!edges.isEmpty()) {
            if (!edges.startsWith("(")) {
                throw new UnparseableGraphDataException("Edge data should be at '(' bracket");
            }
            int index = edges.indexOf(")");
            if (index == -1) {
                throw new UnparseableGraphDataException("Unclosed bracket found");
            }
            String pair = edges.substring(1, index);
            try {
                String[] parsed = COMMA.split(pair);
                if (parsed.length != 2) {
                    throw new NumberFormatException();
                }
                graph.addEdge(new Edge(parseInt(parsed[0].trim()), parseInt(parsed[1].trim())));
            } catch (NumberFormatException ex) {
                throw new UnparseableGraphDataException("Two integer values inside brackets are accepted");
            } catch (IllegalArgumentException ex) {
                throw new UnparseableGraphDataException(ex.getMessage());
            }
            index++;
            if (index == edges.length()) {
                edges = "";
            } else if (edges.charAt(index) != ',') {
                throw new UnparseableGraphDataException("After ')' it should be comma");
            } else {
                index++;
                edges = edges.substring(index);
            }
        }
        return graph;
    }
}
