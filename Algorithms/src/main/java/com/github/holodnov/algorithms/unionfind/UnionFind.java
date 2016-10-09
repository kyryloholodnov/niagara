package com.github.holodnov.algorithms.unionfind;

/**
 * A <tt>UnionFind</tt> is an implementation of union-find data structure. The
 * amortized time complexity for <b>union</b> and <b>find</b> operations has
 * inverse Ackermann complexity.
 *
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://en.wikipedia.org/wiki/Disjoint-set_data_structure">http://en.wikipedia.org/wiki/Disjoint-set_data_structure</a>
 */
public class UnionFind {

    private final int n;
    private final int[] roots;
    private final int[] ranks;
    private final int[] sizes;

    private int subsetsCount;

    /**
     * Creates union-find data structure with <b>n</b> elements and each element
     * in its own subset.
     *
     * @param n input number of elements
     * @throws IllegalArgumentException if input parameter is not positive
     */
    public UnionFind(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Initial number of components should be positive");
        }
        this.n = n;
        subsetsCount = n;
        roots = new int[n];
        ranks = new int[n];
        sizes = new int[n];
        for (int i = 0; i < roots.length; i++) {
            roots[i] = i;
            sizes[i] = 1;
        }
    }

    /**
     * Determines which subset an input element is in and returns root of this
     * subset.
     *
     * @param i input element
     * @return root of subset an input element is in
     * @throws IllegalArgumentException if input element out of bounds
     */
    public int find(int i) {
        if (i < 0 || i >= n) {
            throw new IllegalArgumentException("Input element "
                    + "should be between 0 (inclusive) "
                    + "and " + n + " (exclusive)");
        }
        while (i != roots[i]) {
            roots[i] = roots[roots[i]];
            i = roots[i];
        }
        return i;
    }

    /**
     * Joins two subsets represented by its elements into a single subset.
     *
     * @param i input element from first subset
     * @param j input element from second subset
     * @return root of subset after joining these subsets
     * @throws IllegalArgumentException if input elements out of bounds
     */
    public int union(int i, int j) {
        int firstRoot = find(i);
        int secondRoot = find(j);
        if (firstRoot == secondRoot) {
            return firstRoot;
        }
        if (ranks[firstRoot] < ranks[secondRoot]) {
            roots[firstRoot] = secondRoot;
            sizes[secondRoot] += sizes[firstRoot];
        } else if (ranks[firstRoot] > ranks[secondRoot]) {
            roots[secondRoot] = firstRoot;
            sizes[firstRoot] += sizes[secondRoot];
        } else {
            roots[firstRoot] = secondRoot;
            sizes[secondRoot] += sizes[firstRoot];
            ranks[secondRoot]++;
        }
        subsetsCount--;
        return roots[firstRoot];
    }

    /**
     * Determines subset size an input element is in.
     *
     * @param i input element
     * @return size of subset an input element is in
     * @throws IllegalArgumentException if input element out of bounds
     */
    public int getSubsetSize(int i) {
        return sizes[find(i)];
    }

    /**
     * Returns current number of subsets.
     *
     * @return number of subsets
     */
    public int getSubsetsCount() {
        return subsetsCount;
    }
}
