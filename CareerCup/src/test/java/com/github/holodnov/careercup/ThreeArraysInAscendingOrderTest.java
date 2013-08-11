package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.ThreeArraysInAscendingOrder.getMinimumSum;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=6581732438441984">http://www.careercup.com/question?id=6581732438441984</a>
 * 
 * @author Kyrylo Holodnov
 */
public class ThreeArraysInAscendingOrderTest {
    @Test(expected = IllegalArgumentException.class)
    public void testGetMinimumSumFirstArrayIsNull() {
	getMinimumSum(null, new int[] { 1 }, new int[] { 2 });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMinimumSumFirstArrayIsEmpty() {
	getMinimumSum(new int[0], new int[] { 1 }, new int[] { 2 });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMinimumSumSecondArrayIsNull() {
	getMinimumSum(new int[] { 1 }, null, new int[] { 2 });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMinimumSumSecondArrayIsEmpty() {
	getMinimumSum(new int[] { 1 }, new int[0], new int[] { 2 });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMinimumSumThirdArrayIsNull() {
	getMinimumSum(new int[] { 1 }, new int[] { 2 }, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMinimumSumThirdArrayIsEmpty() {
	getMinimumSum(new int[] { 1 }, new int[] { 2 }, new int[0]);
    }

    @Test
    public void testGetMinimumSum1() {
	int[] first = new int[] { 1, 2, 5 };
	int[] second = new int[] { 0, 2, 7 };
	int[] third = new int[] { 1, 3, 5 };
	assertThat(getMinimumSum(first, second, third), is(2));
    }

    @Test
    public void testGetMinimumSum2() {
	int[] first = new int[] { 1, 2, 5 };
	int[] second = new int[] { 0, 2, 7 };
	int[] third = new int[] { -3, -1, 0 };
	assertThat(getMinimumSum(first, second, third), is(2));
    }

    @Test
    public void testGetMinimumSum3() {
	int[] first = new int[] { 1, 2, 5, 6, 7 };
	int[] second = new int[] { 0, 2, 7 };
	int[] third = new int[] { -3, -1, 7 };
	assertThat(getMinimumSum(first, second, third), is(0));
    }

    @Test
    public void testGetMinimumSum4() {
	int[] first = new int[] { 1, 2, 5, 6, 7 };
	int[] second = new int[] { 0, 2 };
	int[] third = new int[] { -3, -1, 7 };
	assertThat(getMinimumSum(first, second, third), is(4));
    }
}
