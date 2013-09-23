package com.github.holodnov.careercup;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=22345666">http://www.careercup.com/question?id=22345666</a>
 * 
 * @author Kyrylo Holodnov
 */
public class ShortestPathInBinaryTree {

    public static class BinaryTree {

	public final BinaryTree parent;

	public BinaryTree(BinaryTree parent) {
	    this.parent = parent;
	}
    }

    public static int getShortestPath(BinaryTree first, BinaryTree second) {
	if ((first == null) || (second == null)) {
	    throw new IllegalArgumentException(
		    "Input parameters should be not null");
	}
	if (first == second) {
	    return 0;
	}
	int deep1 = getNodeLevel(first);
	int deep2 = getNodeLevel(second);
	int res = 0;
	while (deep1 > deep2) {
	    first = first.parent;
	    deep1--;
	    res++;
	}
	while (deep2 > deep1) {
	    second = second.parent;
	    deep2--;
	    res++;
	}
	while (first != second) {
	    first = first.parent;
	    second = second.parent;
	    res += 2;
	}
	return res;
    }

    private static int getNodeLevel(BinaryTree tree) {
	int level = 0;
	while (tree.parent != null) {
	    tree = tree.parent;
	    level++;
	}
	return level;
    }
}
