package com.github.holodnov.algorithms.graph;

import java.util.Set;

/**
 * @author Kyrylo Holodnov
 */
public class ClusteringResult {

    private final Set<Integer>[] clusters;
    private final double maxSpacing;

    public ClusteringResult(Set<Integer>[] clusters, double maxSpacing) {
        this.clusters = clusters;
        this.maxSpacing = maxSpacing;
    }

    public Set<Integer>[] getClusters() {
        return clusters;
    }

    public double getMaxSpacing() {
        return maxSpacing;
    }
}
