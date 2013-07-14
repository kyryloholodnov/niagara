package com.github.holodnov.careercup;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=21444667">http://www.careercup.com/question?id=21444667</a>
 * 
 * @author Kyrylo Holodnov
 */
public class TreeContainsSubtree {

    public static final class BinaryTree {
	private BinaryTree left;
	private BinaryTree right;
	private int value;

	public BinaryTree(int value, BinaryTree left, BinaryTree right) {
	    this.value = value;
	    this.left = left;
	    this.right = right;
	}

	public BinaryTree getLeft() {
	    return left;
	}

	public void setLeft(BinaryTree left) {
	    this.left = left;
	}

	public BinaryTree getRight() {
	    return right;
	}

	public void setRight(BinaryTree right) {
	    this.right = right;
	}

	public int getValue() {
	    return value;
	}

	public int setValue(int value) {
	    return this.value = value;
	}

    }

    public static boolean containsTree(BinaryTree haystack, BinaryTree needle) {
	if ((haystack == needle) || (needle == null)) {
	    return true;
	}
	return subTree(haystack, needle);
    }

    private static boolean subTree(BinaryTree haystack, BinaryTree needle) {
	if (haystack == null) {
	    return false;
	}
	if (haystack.value == needle.value) {
	    if (matchTree(haystack, needle)) {
		return true;
	    }
	}
	return (subTree(haystack.left, needle) || subTree(haystack.right,
		needle));
    }

    private static boolean matchTree(BinaryTree haystack, BinaryTree needle) {
	if ((haystack == null) && (needle == null)) {
	    return true;
	}
	if ((haystack == null) || (needle == null)) {
	    return false;
	}
	if (haystack.value != needle.value) {
	    return false;
	}
	return (matchTree(haystack.left, needle.left) && matchTree(
		haystack.right, needle.right));
    }
}
