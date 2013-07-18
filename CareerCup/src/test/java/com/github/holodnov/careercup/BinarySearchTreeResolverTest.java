package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.BinarySearchTreeResolver.isBinarySearchTree;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.holodnov.careercup.BinarySearchTreeResolver.BinaryTree;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=22234664">http://www.careercup.com/question?id=22234664</a>
 * 
 * @author Kyrylo Holodnov
 */
public class BinarySearchTreeResolverTest {

    @Test(expected = IllegalArgumentException.class)
    public void testIsBinarySearchTree1() {
	isBinarySearchTree((BinaryTree) null);
    }

    @Test
    public void testIsBinarySearchTree2() {
	BinaryTree treeLevel0 = new BinaryTree(null, null, 0);
	assertThat("This binary tree should be BST",
		isBinarySearchTree(treeLevel0), is(true));
    }

    @Test
    public void testIsBinarySearchTree3() {
	BinaryTree treeLevel10 = new BinaryTree(null, null, -1);
	BinaryTree treeLevel11 = new BinaryTree(null, null, 5);
	BinaryTree treeLevel0 = new BinaryTree(treeLevel10, treeLevel11, 0);
	assertThat("This binary tree should be BST",
		isBinarySearchTree(treeLevel0), is(true));
    }

    @Test
    public void testIsBinarySearchTree4() {
	BinaryTree treeLevel20 = new BinaryTree(-10);
	BinaryTree treeLevel21 = new BinaryTree(3);
	BinaryTree treeLevel22 = new BinaryTree(12);
	BinaryTree treeLevel10 = new BinaryTree(treeLevel20, null, -1);
	BinaryTree treeLevel11 = new BinaryTree(treeLevel21, treeLevel22, 5);
	BinaryTree treeLevel0 = new BinaryTree(treeLevel10, treeLevel11, 0);
	assertThat("This binary tree should be BST",
		isBinarySearchTree(treeLevel0), is(true));
    }

    @Test
    public void testIsBinarySearchTree5() {
	BinaryTree treeLevel20 = new BinaryTree(-10);
	BinaryTree treeLevel21 = new BinaryTree(3);
	BinaryTree treeLevel22 = new BinaryTree(12);
	BinaryTree treeLevel10 = new BinaryTree(treeLevel20, null, 1);
	BinaryTree treeLevel11 = new BinaryTree(treeLevel21, treeLevel22, 5);
	BinaryTree treeLevel0 = new BinaryTree(treeLevel10, treeLevel11, 0);
	assertThat("This binary tree should not be BST",
		isBinarySearchTree(treeLevel0), is(false));
    }
}
