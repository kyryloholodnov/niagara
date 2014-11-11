package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.KthValueInBinaryTree.findKthNode;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.holodnov.careercup.KthValueInBinaryTree.BinaryTree;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=5630332351021056">http://www.careercup.com/question?id=5630332351021056</a>
 * 
 * @author Kyrylo Holodnov
 */
public class KthValueInBinaryTreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void testFindKthNodeNullFirstArgument() {
	findKthNode(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindKthNodeNegativeSecondArgument() {
	findKthNode(new BinaryTree<String>("root", null, null), -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindKthNodeZeroSecondArgument() {
	findKthNode(new BinaryTree<String>("root", null, null), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindKthNodeHighSecondArgument() {
	findKthNode(new BinaryTree<String>("root", null, null), 2);
    }

    @Test
    public void testFindKthNodeOneNodeTree() {
	assertThat(findKthNode(new BinaryTree<String>("root", null, null), 1)
		.getValue(), is("root"));
    }

    @Test
    public void testFindKthNodeTwoNodesTree() {
	BinaryTree<String> tree0 = new BinaryTree<String>("tree0", null, null);
	BinaryTree<String> root = new BinaryTree<String>("root", tree0, null);
	assertThat(findKthNode(root, 1).getValue(), is("tree0"));
	assertThat(findKthNode(tree0, 1).getValue(), is("tree0"));
	assertThat(findKthNode(root, 2).getValue(), is("root"));
    }

    @Test
    public void testFindKthNodeThreeNodesTree() {
	BinaryTree<String> tree0 = new BinaryTree<String>("tree0", null, null);
	BinaryTree<String> tree1 = new BinaryTree<String>("tree1", null, null);
	BinaryTree<String> root = new BinaryTree<String>("root", tree0, tree1);
	assertThat(findKthNode(root, 1).getValue(), is("tree0"));
	assertThat(findKthNode(tree0, 1).getValue(), is("tree0"));
	assertThat(findKthNode(tree1, 1).getValue(), is("tree1"));
	assertThat(findKthNode(root, 2).getValue(), is("root"));
	assertThat(findKthNode(root, 3).getValue(), is("tree1"));
	assertThat(findKthNode(tree1, 1).getValue(), is("tree1"));
    }

    @Test
    public void testFindKthNodeSevenNodesTree() {
	BinaryTree<String> tree00 = new BinaryTree<String>("tree00", null, null);
	BinaryTree<String> tree01 = new BinaryTree<String>("tree01", null, null);
	BinaryTree<String> tree0 = new BinaryTree<String>("tree0", tree00,
		tree01);
	BinaryTree<String> tree10 = new BinaryTree<String>("tree10", null, null);
	BinaryTree<String> tree11 = new BinaryTree<String>("tree11", null, null);
	BinaryTree<String> tree1 = new BinaryTree<String>("tree1", tree10,
		tree11);

	BinaryTree<String> root = new BinaryTree<String>("root", tree0, tree1);
	assertThat(findKthNode(root, 1).getValue(), is("tree00"));
	assertThat(findKthNode(root, 2).getValue(), is("tree0"));
	assertThat(findKthNode(root, 3).getValue(), is("tree01"));
	assertThat(findKthNode(root, 4).getValue(), is("root"));
	assertThat(findKthNode(root, 5).getValue(), is("tree10"));
	assertThat(findKthNode(root, 6).getValue(), is("tree1"));
	assertThat(findKthNode(root, 7).getValue(), is("tree11"));

	BinaryTree<String> onlyLeftNode = new BinaryTree<String>("root", tree0,
		null);
	assertThat(findKthNode(onlyLeftNode, 1).getValue(), is("tree00"));
	assertThat(findKthNode(onlyLeftNode, 2).getValue(), is("tree0"));
	assertThat(findKthNode(onlyLeftNode, 3).getValue(), is("tree01"));
	assertThat(findKthNode(onlyLeftNode, 4).getValue(), is("root"));

	BinaryTree<String> onlyRightNode = new BinaryTree<String>("root", null,
		tree1);
	assertThat(findKthNode(onlyRightNode, 1).getValue(), is("root"));
	assertThat(findKthNode(onlyRightNode, 2).getValue(), is("tree10"));
	assertThat(findKthNode(onlyRightNode, 3).getValue(), is("tree1"));
	assertThat(findKthNode(onlyRightNode, 4).getValue(), is("tree11"));
    }
}
