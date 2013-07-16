package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.LeastDifferenceInArrayWithGivenNumber.getElementWithLeastDifference;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=20982670">http://www.careercup.com/question?id=20982670</a>
 * 
 * @author Kyrylo Holodnov
 */
public class LeastDifferenceInArrayWithGivenNumberTest {
    @Test(expected = IllegalArgumentException.class)
    public void testGetElementWithLeastDifferenceNullArray() {
	getElementWithLeastDifference(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetElementWithLeastDifferenceEmptyArray() {
	getElementWithLeastDifference(new int[0], 1);
    }

    @Test
    public void testGetElementWithLeastDifference1() {
	int res = getElementWithLeastDifference(new int[] { 1, 3, 5, 7, 9, 11,
		13, 15 }, 1);
	assertThat("Unexpected result", res, is(1));
    }

    @Test
    public void testGetElementWithLeastDifference2() {
	int res = getElementWithLeastDifference(new int[] { 1, 4, 5, 7, 9, 11,
		13, 15 }, 2);
	assertThat("Unexpected result", res, is(1));
    }

    @Test
    public void testGetElementWithLeastDifference3() {
	int res = getElementWithLeastDifference(new int[] { 1, 3, 5, 7, 9, 11,
		13, 15 }, 2);
	assertThat("Unexpected result", res, is(1));
    }

    @Test
    public void testGetElementWithLeastDifference4() {
	int res = getElementWithLeastDifference(new int[] { 1, 4, 5, 7, 9, 11,
		13, 15 }, 2);
	assertThat("Unexpected result", res, is(1));
    }

    @Test
    public void testGetElementWithLeastDifference5() {
	int res = getElementWithLeastDifference(new int[] { 1, 4, 5, 7, 9, 11,
		13, 15 }, 5);
	assertThat("Unexpected result", res, is(5));
    }

    @Test
    public void testGetElementWithLeastDifference6() {
	int res = getElementWithLeastDifference(new int[] { 1, 4, 5, 7, 9, 11,
		13, 15 }, 14);
	assertThat("Unexpected result", res, is(13));
    }

    @Test
    public void testGetElementWithLeastDifference7() {
	int res = getElementWithLeastDifference(new int[] { 1, 4, 5, 7, 9, 11,
		13, 15 }, 15);
	assertThat("Unexpected result", res, is(15));
    }

    @Test
    public void testGetElementWithLeastDifference8() {
	int res = getElementWithLeastDifference(new int[] { 1, 4, 5, 7, 9, 11,
		13, 15 }, 20);
	assertThat("Unexpected result", res, is(15));
    }
}
