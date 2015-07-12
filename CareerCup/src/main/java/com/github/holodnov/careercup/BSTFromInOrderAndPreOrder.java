package com.github.holodnov.careercup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=21296665">http://www.careercup.com/question?id=21296665</a>
 */
public class BSTFromInOrderAndPreOrder {

    public static class BinarySearchTree {

        private BinarySearchTree left;
        private BinarySearchTree right;
        private int value;

        private BinarySearchTree() {
        }

        public BinarySearchTree getLeft() {
            return left;
        }

        public BinarySearchTree getRight() {
            return right;
        }

        public int getValue() {
            return value;
        }

        public List<Integer> inOrderTraverse() {
            List<Integer> path = new ArrayList<>();
            if (left != null) {
                path.addAll(left.inOrderTraverse());
            }
            path.add(value);
            if (right != null) {
                path.addAll(right.inOrderTraverse());
            }
            return path;
        }

        public List<Integer> preOrderTraverse() {
            List<Integer> path = new ArrayList<>();
            path.add(value);
            if (left != null) {
                path.addAll(left.preOrderTraverse());
            }
            if (right != null) {
                path.addAll(right.preOrderTraverse());
            }
            return path;
        }

        public List<Integer> postOrderTraverse() {
            List<Integer> path = new ArrayList<>();
            if (left != null) {
                path.addAll(left.postOrderTraverse());
            }
            if (right != null) {
                path.addAll(right.postOrderTraverse());
            }
            path.add(value);
            return path;
        }
    }

    public static BinarySearchTree constructTree(int[] inOrder, int[] preOrder) {
        if (inOrder == null || inOrder.length == 0) {
            throw new IllegalArgumentException("InOrder array cannot be null or empty");
        }
        if (preOrder == null || preOrder.length == 0) {
            throw new IllegalArgumentException("PreOrder array cannot be null or empty");
        }
        if (inOrder.length != preOrder.length) {
            throw new IllegalArgumentException("InOrder and PreOrder arrays have different sizes");
        }
        return constructTree(inOrder, 0, inOrder.length, preOrder, 0, preOrder.length);
    }

    private static BinarySearchTree constructTree(int[] inOrder, int inStart,
                                                  int inEnd, int[] preOrder, int preStart, int preEnd) {
        if (preStart >= preEnd || inStart >= inEnd) {
            return null;
        }
        int root;
        for (root = inStart; root < inEnd; root++) {
            if (inOrder[root] == preOrder[preStart]) {
                break;
            }
        }
        BinarySearchTree tree = new BinarySearchTree();
        tree.value = inOrder[root];
        tree.left = constructTree(inOrder, inStart, root, preOrder,
                preStart + 1, preStart + 1 + (root - inStart));
        tree.right = constructTree(inOrder, root + 1, inEnd, preOrder,
                preStart + 1 + (root - inStart), preEnd);
        return tree;
    }
}
