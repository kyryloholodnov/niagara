package com.github.holodnov.careercup;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author Kyrylo Holodnov
 */
public class Knight {

    private static final Pattern BOARD_CELL_PATTERN = Pattern.compile("[a-h][1-8]");
    private static final int BOARD_SIZE = 8;

    public static int findShortestWay(String start, String end) {
        if (start == null || !BOARD_CELL_PATTERN.matcher(start).matches()) {
            throw new IllegalArgumentException("Start position should be 2-char string [a-h][1-8], not " + start);
        }
        if (end == null || !BOARD_CELL_PATTERN.matcher(end).matches()) {
            throw new IllegalArgumentException("End position should be 2-char string [a-h][1-8], not " + end);
        }
        int sh = start.charAt(0) - 'a';
        int sg = start.charAt(1) - '1';
        int eh = end.charAt(0) - 'a';
        int eg = end.charAt(1) - '1';
        NonDirectedGraph graph = new NonDirectedGraph(BOARD_SIZE * BOARD_SIZE);
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (i - 2 >= 0 && j - 1 >= 0) {
                    graph.addEdge(BOARD_SIZE * (i - 2) + (j - 1), BOARD_SIZE * i + j);
                }
                if (i - 2 >= 0 && j + 1 < BOARD_SIZE) {
                    graph.addEdge(BOARD_SIZE * (i - 2) + (j + 1), BOARD_SIZE * i + j);
                }
                if (i - 1 >= 0 && j - 2 >= 0) {
                    graph.addEdge(BOARD_SIZE * (i - 1) + (j - 2), BOARD_SIZE * i + j);
                }
                if (i - 1 >= 0 && j + 2 < BOARD_SIZE) {
                    graph.addEdge(BOARD_SIZE * (i - 1) + (j + 2), BOARD_SIZE * i + j);
                }
            }
        }
        return graph.bfs(sh * BOARD_SIZE + sg, eh * BOARD_SIZE + eg);
    }

    private static class NonDirectedGraph {

        private final List<Edge>[] edges;

        public NonDirectedGraph(int vertexCount) {
            edges = new List[vertexCount];
        }

        public void addEdge(int from, int to) {
            if (edges[from] == null) {
                edges[from] = new ArrayList<>();
            }
            edges[from].add(new Edge(from, to));
            if (edges[to] == null) {
                edges[to] = new ArrayList<>();
            }
            edges[to].add(new Edge(to, from));
        }

        public int bfs(int from, int to) {
            if (from == to) {
                return 0;
            }
            int[] path = new int[edges.length];
            Arrays.fill(path, -1);
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(from);
            path[from] = 0;
            while (!queue.isEmpty()) {
                int current = queue.poll();
                List<Edge> currentEdges = edges[current];
                if (currentEdges == null) {
                    continue;
                }
                for (Edge currentEdge : currentEdges) {
                    int edgeToVertex = currentEdge.toVertex;
                    if (path[edgeToVertex] > -1) {
                        continue;
                    }
                    path[edgeToVertex] = path[current] + 1;
                    if (edgeToVertex == to) {
                        return path[edgeToVertex];
                    }
                    queue.offer(edgeToVertex);
                }
            }
            return -1;
        }
    }

    private static class Edge {

        final int fromVertex;
        final int toVertex;

        public Edge(int fromVertex, int toVertex) {
            this.fromVertex = fromVertex;
            this.toVertex = toVertex;
        }
    }
}
