package com.github.holodnov.careercup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * @author Kyrylo Holodnov
 */
public class Knight {
    private final static int BOARD_SIZE = 8;

    public static int findShortestWay(String start, String end) {
	if ((start == null) || (start.length() != 2)) {
	    throw new IllegalArgumentException(
		    "Start position should be 2-char string, not " + start);
	}
	if ((end == null) || (end.length() != 2)) {
	    throw new IllegalArgumentException(
		    "End position should be 2-char string, not " + end);
	}
	int sh = start.charAt(0) - 'a';
	int sg = start.charAt(1) - '1';
	int eh = end.charAt(0) - 'a';
	int eg = end.charAt(1) - '1';
	if ((sh >= BOARD_SIZE) || (sh < 0) || (sg >= BOARD_SIZE) || (sg < 0)) {
	    throw new IllegalArgumentException(
		    "Start position is 2-char string,"
			    + " but does not represent border ceil: " + start);
	}
	if ((eh >= BOARD_SIZE) || (eh < 0) || (eg >= BOARD_SIZE) || (eg < 0)) {
	    throw new IllegalArgumentException("End position is 2-char string,"
		    + " but does not represent border ceil: " + end);
	}
	NonDirectedGraph graph = new NonDirectedGraph(BOARD_SIZE * BOARD_SIZE);
	for (int i = 0; i < BOARD_SIZE; i++) {
	    for (int j = 0; j < BOARD_SIZE; j++) {
		if ((i - 2 >= 0) && (j - 1 >= 0)) {
		    graph.addEdge(BOARD_SIZE * (i - 2) + (j - 1), BOARD_SIZE
			    * i + j);
		}
		if ((i - 2 >= 0) && (j + 1 < BOARD_SIZE)) {
		    graph.addEdge(BOARD_SIZE * (i - 2) + (j + 1), BOARD_SIZE
			    * i + j);
		}
		if ((i - 1 >= 0) && (j - 2 >= 0)) {
		    graph.addEdge(BOARD_SIZE * (i - 1) + (j - 2), BOARD_SIZE
			    * i + j);
		}
		if ((i - 1 >= 0) && (j + 2 < BOARD_SIZE)) {
		    graph.addEdge(BOARD_SIZE * (i - 1) + (j + 2), BOARD_SIZE
			    * i + j);
		}
	    }
	}
	int res = graph.bfs(sh * BOARD_SIZE + sg, eh * BOARD_SIZE + eg);
	return res;
    }

    public static class NonDirectedGraph {

	private List<Edge>[] edges;

	public NonDirectedGraph(int vertexCount) {
	    edges = new List[vertexCount];
	}

	public void addEdge(int from, int to) {
	    if (edges[from] == null) {
		edges[from] = new ArrayList<Edge>();
	    }
	    edges[from].add(new Edge(from, to));
	    if (edges[to] == null) {
		edges[to] = new ArrayList<Edge>();
	    }
	    edges[to].add(new Edge(to, from));
	}

	public int bfs(int from, int to) {
	    if (from == to) {
		return 0;
	    }
	    int[] path = new int[edges.length];
	    Arrays.fill(path, -1);
	    Queue<Integer> queue = new LinkedList<Integer>();
	    queue.offer(from);
	    path[from] = 0;
	    while (!queue.isEmpty()) {
		int current = queue.poll();
		List<Edge> currentEdges = edges[current];
		if (currentEdges == null) {
		    continue;
		}
		for (int i = 0; i < currentEdges.size(); i++) {
		    int edgeToVertex = currentEdges.get(i).toVertex;
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

    public static class Edge {

	public final int fromVertex;
	public final int toVertex;

	public Edge(int fromVertex, int toVertex) {
	    this.fromVertex = fromVertex;
	    this.toVertex = toVertex;
	}
    }
}
