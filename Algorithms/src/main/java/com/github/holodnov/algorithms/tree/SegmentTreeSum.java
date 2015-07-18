package com.github.holodnov.algorithms.tree;

/**
 * @author Kyrylo Holodnov
 */
public class SegmentTreeSum {

    private final int size;
    private final long[] values;
    private final long[] sum;

    public SegmentTreeSum(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Input size should be positive value");
        }
        this.size = size;
        this.values = new long[size << 2];
        this.sum = new long[size << 2];
    }

    /**
     * Adds new value to all elements in [left, right) interval.
     *
     * @param left  left bound
     * @param right right bound
     * @param value value to add
     */
    public void addValue(int left, int right, long value) {
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
     * Finds a sum on interval [left, right).
     *
     * @param left  left bound
     * @param right right bound
     * @return the sum on interval
     */
    public long getSum(int left, int right) {
        if (left < 0 || right > size) {
            throw new IllegalArgumentException(
                    "Input left and round bounds should be between zero and segment tree size");
        }
        if (right <= left) {
            throw new IllegalArgumentException(
                    "Input right bound should be greater than left bound");
        }
        return getSum(left, right, 0, 0, size);
    }

    private void addValue(int left, int right, long value, int pos, int treeLeft, int treeRight) {
        sum[pos] += value * (right - left);
        if (left == treeLeft && right == treeRight) {
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
    }

    private long getSum(int left, int right, int pos, int treeLeft, int treeRight) {
        if (left == treeLeft && right == treeRight) {
            return sum[pos];
        }
        int treeMedian = (treeLeft + treeRight) >>> 1;
        if (right <= treeMedian) {
            return getSum(left, right, 2 * pos + 1, treeLeft, treeMedian) + (right - left) * values[pos];
        }
        if (left >= treeMedian) {
            return getSum(left, right, 2 * pos + 2, treeMedian, treeRight) + (right - left) * values[pos];
        }
        return getSum(left, treeMedian, 2 * pos + 1, treeLeft, treeMedian) +
                getSum(treeMedian, right, 2 * pos + 2, treeMedian, treeRight) +
                (right - left) * values[pos];
    }
}
