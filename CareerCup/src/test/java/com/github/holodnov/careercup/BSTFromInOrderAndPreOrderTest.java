package com.github.holodnov.careercup;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.holodnov.careercup.BSTFromInOrderAndPreOrder.BinarySearchTree;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=21296665">http://www.careercup.com/question?id=21296665</a>
 * 
 * @author Kyrylo Holodnov
 */
public class BSTFromInOrderAndPreOrderTest {
    @Test
    public void testConstructTree1() {
	int[] inOrder = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	int[] preOrder = new int[] { 3, 2, 1, 7, 4, 6, 5, 8, 9 };
	BinarySearchTree tree = BSTFromInOrderAndPreOrder.constructTree(
		inOrder, preOrder);
	assertThat("PreOrder check failed", tree.preOrderTraverse().toArray(),
		is(new Object[] { 3, 2, 1, 7, 4, 6, 5, 8, 9 }));
	assertThat("InOrder check failed", tree.inOrderTraverse().toArray(),
		is(new Object[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }));
	assertThat("PostOrder check failed",
		tree.postOrderTraverse().toArray(), is(new Object[] { 1, 2, 5,
			6, 4, 9, 8, 7, 3 }));
    }

    @Test
    public void testConstructTree2() {
	int[] inOrder = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	int[] preOrder = new int[] { 9, 3, 1, 2, 7, 5, 4, 6, 8 };
	BinarySearchTree tree = BSTFromInOrderAndPreOrder.constructTree(
		inOrder, preOrder);
	assertThat("PreOrder check failed", tree.preOrderTraverse().toArray(),
		is(new Object[] { 9, 3, 1, 2, 7, 5, 4, 6, 8 }));
	assertThat("InOrder check failed", tree.inOrderTraverse().toArray(),
		is(new Object[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }));
	assertThat("PostOrder check failed",
		tree.postOrderTraverse().toArray(), is(new Object[] { 2, 1, 4,
			6, 5, 8, 7, 3, 9 }));
    }
}
