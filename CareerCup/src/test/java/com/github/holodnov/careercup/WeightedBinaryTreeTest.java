package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.WeightedBinaryTree.getMinimumTreeWeight;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=24890664">http://www.careercup.com/question?id=24890664</a>
 * 
 * @author Kyrylo Holodnov
 */
public class WeightedBinaryTreeTest {
    @Test(expected = IllegalArgumentException.class)
    public void testWeightedBinaryTreeNullArray() {
	getMinimumTreeWeight(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWeightedBinaryTreeEmptyArray() {
	getMinimumTreeWeight(new int[0]);
    }

    @Test
    public void testWeightedBinaryTree1() {
	assertThat(getMinimumTreeWeight(new int[] { 5 }), is(5));
    }

    @Test
    public void testWeightedBinaryTree2() {
	assertThat(getMinimumTreeWeight(new int[] { 5, 3 }), is(11));
    }

    @Test
    public void testWeightedBinaryTree3() {
	assertThat(getMinimumTreeWeight(new int[] { 4, 5, 3 }), is(19));
    }

    @Test
    public void testWeightedBinaryTree4() {
	assertThat(getMinimumTreeWeight(new int[] { 4, 5, 3, 4 }), is(30));
    }

    @Test
    public void testWeightedBinaryTree5() {
	assertThat(getMinimumTreeWeight(new int[] { 4, 5, 3, 7, 4 }), is(46));
    }

    @Test
    public void testWeightedBinaryTree6() {
	assertThat(
		getMinimumTreeWeight(new int[] { 4, 5, 100, 3, 7, 4, 5, 7 }),
		is(194));
    }

    @Test
    public void testWeightedBinaryTree7() {
	assertThat(getMinimumTreeWeight(new int[] { 1, 1, 1, 1, 1, 1, 1, 1 }),
		is(21));
    }
}
