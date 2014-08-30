package com.github.holodnov.algorithms.graph;

import java.util.Set;

/**
 * @author Kyrylo Holodnov
 */
public class ClusteringResult {

    private final Set<Integer>[] clusters;
    private final double maxSpacing;
    private final int maxSpacingFirstVertex;
    private final int maxSpacingSecondVertex;

    public ClusteringResult(Set<Integer>[] clusters, double maxSpacing, int maxSpacingFirstVertex, int maxSpacingSecondVertex) {
        this.clusters = clusters;
        this.maxSpacing = maxSpacing;
        this.maxSpacingFirstVertex = maxSpacingFirstVertex;
        this.maxSpacingSecondVertex = maxSpacingSecondVertex;
    }

    public Set<Integer>[] getClusters() {
        return clusters;
    }

    public double getMaxSpacing() {
        return maxSpacing;
    }

    public int getMaxSpacingFirstVertex() {
        return maxSpacingFirstVertex;
    }

    public int getMaxSpacingSecondVertex() {
        return maxSpacingSecondVertex;
    }
}
