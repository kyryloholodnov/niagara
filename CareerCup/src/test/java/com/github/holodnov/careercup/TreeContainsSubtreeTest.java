package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.TreeContainsSubtree.containsTree;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.holodnov.careercup.TreeContainsSubtree.BinaryTree;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=21444667">http://www.careercup.com/question?id=21444667</a>
 * 
 * @author Kyrylo Holodnov
 */
public class TreeContainsSubtreeTest {
    @Test
    public void testContainsTree1() {
	BinaryTree haystack = new BinaryTree(1, new BinaryTree(2,
		new BinaryTree(3, null, null), null), new BinaryTree(2,
		new BinaryTree(3, null, null), null));
	BinaryTree needle = new BinaryTree(3, null, null);
	assertThat("Expected result: contains = true",
		containsTree(haystack, needle), is(true));
    }

    @Test
    public void testContainsTree2() {
	BinaryTree haystack = new BinaryTree(1, new BinaryTree(2,
		new BinaryTree(3, null, null), null), new BinaryTree(2,
		new BinaryTree(3, null, null), null));
	BinaryTree needle = new BinaryTree(2, null, null);
	assertThat("Expected result: contains = false",
		containsTree(haystack, needle), is(false));
    }

    @Test
    public void testContainsTree3() {
	BinaryTree haystack = new BinaryTree(1, new BinaryTree(2,
		new BinaryTree(3, null, null), new BinaryTree(4, null, null)),
		new BinaryTree(2, new BinaryTree(3, null, null), null));
	BinaryTree needle = new BinaryTree(4, null, null);
	assertThat("Expected result: contains = true",
		containsTree(haystack, needle), is(true));
    }

    @Test
    public void testContainsTree4() {
	BinaryTree haystack = new BinaryTree(1, new BinaryTree(2,
		new BinaryTree(3, null, null), new BinaryTree(4, null, null)),
		new BinaryTree(2, new BinaryTree(3, null, null), null));
	BinaryTree needle = new BinaryTree(4, null, new BinaryTree(5, null,
		null));
	assertThat("Expected result: contains = false",
		containsTree(haystack, needle), is(false));
    }
}
