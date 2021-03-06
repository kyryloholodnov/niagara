package com.github.holodnov.careercup;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=22234664">http://www.careercup.com/question?id=22234664</a>
 */
public class BinarySearchTreeResolver {

    public static class BinaryTree {

        public final BinaryTree left;
        public final BinaryTree right;
        public final int value;

        public BinaryTree(BinaryTree left, BinaryTree right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public BinaryTree(int value) {
            this(null, null, value);
        }
    }

    public static boolean isBinarySearchTree(BinaryTree tree) {
        if (tree == null) {
            throw new IllegalArgumentException("Input binary tree is null");
        }
        if (tree.left != null) {
            int lValue = max(tree.left);
            if (lValue >= tree.value) {
                return false;
            }
        }
        if (tree.right != null) {
            int rValue = min(tree.right);
            if (rValue < tree.value) {
                return false;
            }
        }
        return true;
    }

    private static int max(BinaryTree tree) {
        int max = tree.value;
        if (tree.left != null) {
            max = Math.max(max, max(tree.left));
        }
        if (tree.right != null) {
            max = Math.max(max, max(tree.right));
        }
        return max;
    }

    private static int min(BinaryTree tree) {
        int min = tree.value;
        if (tree.left != null) {
            min = Math.min(min, min(tree.left));
        }
        if (tree.right != null) {
            min = Math.min(min, min(tree.right));
        }
        return min;
    }
}
