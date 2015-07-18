package com.github.holodnov.algorithms.tree;

import static java.lang.Math.min;

/**
 * @author Kyrylo Holodnov
 */
public class SegmentTreeMin {

    private final int size;
    private final long[] values;
    private final long[] min;

    public SegmentTreeMin(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Input size should be positive value");
        }
        this.size = size;
        this.values = new long[size << 2];
        this.min = new long[size << 2];
    }

    /**
     * Adds new value to all elements in [left, right) interval.
     *
     * @param left  left bound
     * @param right right bound
     * @param value value to add
     */
    public void addValue(int left, int right, int value) {
        if (left < 0 || right > size) {
            throw new IllegalArgumentException(
                    "Input left and round bounds should be between zero and segment tree size");
        }
        if (right <= left) {
            throw new IllegalArgumentException(
                    "Input right bound should be greater than left bound");
        }
        addValue(left, right, value, 0, 0, size);
    }

    /**
     * Finds a minimum value on interval [left, right).
     *
     * @param left  left bound
     * @param right right bound
     * @return the minimum on interval
     */
    public long getMinimum(int left, int right) {
        if (left < 0 || right > size) {
            throw new IllegalArgumentException(
                    "Input left and round bounds should be between zero and segment tree size");
        }
        if (right <= left) {
            throw new IllegalArgumentException(
                    "Input right bound should be greater than left bound");
        }
        return getMinimum(left, right, 0, 0, size);
    }

    private void addValue(int left, int right, int value, int pos, int treeLeft, int treeRight) {
        if (left == treeLeft && right == treeRight) {
            min[pos] += value;
            values[pos] += value;
            return;
        }
        int treeMedian = (treeLeft + treeRight) >>> 1;
        if (right <= treeMedian) {
            addValue(left, right, value, 2 * pos + 1, treeLeft, treeMedian);
        } else if (left >= treeMedian) {
            addValue(left, right, value, 2 * pos + 2, treeMedian, treeRight);
        } else {
            addValue(left, treeMedian, value, 2 * pos + 1, treeLeft, treeMedian);
            addValue(treeMedian, right, value, 2 * pos + 2, treeMedian, treeRight);
        }
        min[pos] = min(min[2 * pos + 1], min[2 * pos + 2]) + values[pos];
    }

    private long getMinimum(int left, int right, int pos, int treeLeft, int treeRight) {
        if (left == treeLeft && right == treeRight) {
            return min[pos];
        }
        int treeMedian = (treeLeft + treeRight) >>> 1;
        if (right <= treeMedian) {
            return getMinimum(left, right, 2 * pos + 1, treeLeft, treeMedian) + values[pos];
        }
        if (left >= treeMedian) {
            return getMinimum(left, right, 2 * pos + 2, treeMedian, treeRight) + values[pos];
        }
        return min(getMinimum(left, treeMedian, 2 * pos + 1, treeLeft, treeMedian),
                getMinimum(treeMedian, right, 2 * pos + 2, treeMedian, treeRight)) + values[pos];
    }
}
