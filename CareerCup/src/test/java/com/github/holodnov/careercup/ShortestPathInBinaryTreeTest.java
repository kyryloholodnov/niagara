package com.github.holodnov.careercup;

import com.github.holodnov.careercup.ShortestPathInBinaryTree.BinaryTree;
import org.junit.Test;

import static com.github.holodnov.careercup.ShortestPathInBinaryTree.getShortestPath;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=22345666">http://www.careercup.com/question?id=22345666</a>
 */
public class ShortestPathInBinaryTreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void testGetShortestPathFirstNodeIsNull() {
        BinaryTree tree = new BinaryTree(null);
        getShortestPath(null, tree);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetShortestPathSecondNodeIsNull() {
        BinaryTree tree = new BinaryTree(null);
        getShortestPath(tree, null);
    }

    @Test
    public void testGetShortestPathSameNodes() {
        BinaryTree root = new BinaryTree(null);
        BinaryTree node1 = new BinaryTree(root);
        assertThat(getShortestPath(node1, node1), is(0));
    }

    @Test
    public void testGetShortestPath() {
        BinaryTree root = new BinaryTree(null);
        BinaryTree node11 = new BinaryTree(root);
        BinaryTree node12 = new BinaryTree(root);
        BinaryTree node21 = new BinaryTree(node11);
        BinaryTree node22 = new BinaryTree(node11);
        BinaryTree node23 = new BinaryTree(node12);
        BinaryTree node24 = new BinaryTree(node12);
        BinaryTree node31 = new BinaryTree(node21);
        assertThat(getShortestPath(root, root), is(0));
        assertThat(getShortestPath(node21, node22), is(2));
        assertThat(getShortestPath(node12, node22), is(3));
        assertThat(getShortestPath(node23, node24), is(2));
        assertThat(getShortestPath(root, node12), is(1));
        assertThat(getShortestPath(root, node24), is(2));
        assertThat(getShortestPath(node11, node24), is(3));
        assertThat(getShortestPath(node11, node21), is(1));
        assertThat(getShortestPath(node21, node24), is(4));
        assertThat(getShortestPath(node31, node21), is(1));
        assertThat(getShortestPath(node31, node12), is(4));
        assertThat(getShortestPath(node31, root), is(3));
        assertThat(getShortestPath(node31, node12), is(4));
        assertThat(getShortestPath(node31, node24), is(5));
    }
}
