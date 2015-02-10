package com.github.holodnov.algorithms.graph;

import java.io.Serializable;

/**
 * @author Kyrylo Holodnov
 */
public class Edge implements Serializable {

    static final long serialVersionUID = 201502100251L;

    private final int tail;
    private final int head;
    private final double weight;

    public Edge(int tail, int head, double weight) {
        this.tail = tail;
        this.head = head;
        this.weight = weight;
    }

    public Edge(int tail, int head) {
        this(tail, head, 1.0);
    }

    public Edge reverse() {
        return new Edge(head, tail, weight);
    }

    public int getTail() {
        return tail;
    }

    public int getHead() {
        return head;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "tail=" + tail +
                ", head=" + head +
                ", weight=" + weight +
                '}';
    }
}
