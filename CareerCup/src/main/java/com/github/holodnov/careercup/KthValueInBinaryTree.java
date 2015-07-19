package com.github.holodnov.careercup;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=5630332351021056">http://www.careercup.com/question?id=5630332351021056</a>
 */
public class KthValueInBinaryTree {

    public static <V> BinaryTree<V> findKthNode(BinaryTree<V> tree, int k) {
        if (tree == null) {
            throw new IllegalArgumentException("Input binary tree should be not null");
        }
        if (k < 1) {
            throw new IllegalArgumentException("Input value k should be greater than 0");
        }
        if (k > tree.nodesCount) {
            throw new IllegalArgumentException("Input value k should be less or equal to nodes count in binary tree");
        }
        while (tree.getNodesCount() - getNodesCountOfNullableTree(tree.right) != k) {
            int leftBranchNodesCount = getNodesCountOfNullableTree(tree.left);
            if (k <= leftBranchNodesCount) {
                tree = tree.left;
            } else {
                k -= leftBranchNodesCount + 1;
                tree = tree.right;
            }
        }
        return tree;
    }

    private static int getNodesCountOfNullableTree(BinaryTree tree) {
        return tree == null ? 0 : tree.nodesCount;
    }

    public static class BinaryTree<V> {

        private final V value;
        private final BinaryTree<V> left;
        private final BinaryTree<V> right;
        private final int nodesCount;

        public BinaryTree(V value, BinaryTree<V> left, BinaryTree<V> right) {
            if (left == right && left != null) {
                throw new IllegalArgumentException("Left and right nodes should not reference to one binary tree");
            }
            this.value = value;
            this.left = left;
            this.right = right;
            this.nodesCount = 1 + getNodesCountOfNullableTree(left) + getNodesCountOfNullableTree(right);
        }

        public V getValue() {
            return value;
        }

        public BinaryTree<V> getLeft() {
            return left;
        }

        public BinaryTree<V> getRight() {
            return right;
        }

        public int getNodesCount() {
            return nodesCount;
        }
    }
}
