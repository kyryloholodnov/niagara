package com.github.holodnov.careercup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=24157662">http://www.careercup.com/question?id=24157662</a>
 */
public class LeftmostElementOfEachLevelInBinaryTree {

    public static List<Integer> findLeftmostElements(BinaryTree root) {
        if (root == null) {
            throw new IllegalArgumentException("Input tree is null");
        }
        ArrayList<Integer> values = new ArrayList<>();
        Queue<Object[]> queue = new LinkedList<>();
        queue.add(new Object[]{root, 0});
        int level = 0;
        while (!queue.isEmpty()) {
            Object[] obj = queue.poll();
            BinaryTree currentTree = (BinaryTree) obj[0];
            int currentLevel = (Integer) obj[1];
            if (level == currentLevel) {
                values.add(currentTree.data);
                level++;
            }
            if (currentTree.left != null) {
                queue.add(new Object[]{currentTree.left, currentLevel + 1});
            }
            if (currentTree.right != null) {
                queue.add(new Object[]{currentTree.right, currentLevel + 1});
            }
        }
        return values;
    }

    public static class BinaryTree {

        public final BinaryTree left;
        public final BinaryTree right;
        public final int data;

        public BinaryTree(BinaryTree left, BinaryTree right, int data) {
            this.left = left;
            this.right = right;
            this.data = data;
        }
    }
}
