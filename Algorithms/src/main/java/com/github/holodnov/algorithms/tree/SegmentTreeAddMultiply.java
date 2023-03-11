package com.github.holodnov.algorithms.tree;

import java.util.Arrays;
import java.util.function.BiFunction;

/**
 * @author Kyrylo Holodnov
 */
public class SegmentTreeAddMultiply {

    private final int n;
    private final long[] tree;
    private final long[] lazyMul;
    private final long[] lazyAdd;
    private final BiFunction<Long, Long, Long> operation;
    private final Long nullValue;

    public SegmentTreeAddMultiply(int n, BiFunction<Long, Long, Long> operation, Long nullValue) {
        this.n = n;
        this.tree = new long[4 * n];
        this.lazyMul = new long[4 * n];
        this.lazyAdd = new long[4 * n];
        this.operation = operation;
        this.nullValue = nullValue;
        Arrays.fill(tree, nullValue);
    }

    private void propagate(int node, int left, int right) {
        if (lazyMul[node] != 1) {
            tree[node] *= lazyMul[node];
            if (left != right) {
                lazyMul[2 * node] *= lazyMul[node];
                lazyMul[2 * node + 1] *= lazyMul[node];
                lazyAdd[2 * node] *= lazyMul[node];
                lazyAdd[2 * node + 1] *= lazyMul[node];
            }
            lazyMul[node] = 1;
        }
        if (lazyAdd[node] != 0) {
            tree[node] += lazyAdd[node] * (right - left + 1);
            if (left != right) {
                lazyAdd[2 * node] += lazyAdd[node];
                lazyAdd[2 * node + 1] += lazyAdd[node];
            }
            lazyAdd[node] = 0;
        }
    }

    private void update(int node, int left, int right, int ql, int qr, long mul, long add) {
        propagate(node, left, right);
        if (ql > right || qr < left) {
            return;
        }
        if (ql <= left && qr >= right) {
            lazyMul[node] *= mul;
            lazyAdd[node] += add;
            propagate(node, left, right);
            return;
        }
        int mid = (left + right) / 2;
        update(2 * node, left, mid, ql, qr, mul, add);
        update(2 * node + 1, mid + 1, right, ql, qr, mul, add);
        tree[node] = operation.apply(tree[2 * node], tree[2 * node + 1]);
    }

    private long query(int node, int left, int right, int ql, int qr) {
        propagate(node, left, right);
        if (ql > right || qr < left) {
            return nullValue;
        }
        if (ql <= left && qr >= right) {
            return tree[node];
        }
        int mid = (left + right) / 2;
        long leftValue = query(2 * node, left, mid, ql, qr);
        long rightValue = query(2 * node + 1, mid + 1, right, ql, qr);
        return operation.apply(leftValue, rightValue);
    }

    public void updateAdd(int left, int right, long value) {
        update(1, 0, n - 1, left, right, 1, value);
    }

    public void updateMul(int left, int right, long value) {
        update(1, 0, n - 1, left, right, value, 0);
    }

    public long query(int left, int right) {
        return query(1, 0, n - 1, left, right);
    }
}
