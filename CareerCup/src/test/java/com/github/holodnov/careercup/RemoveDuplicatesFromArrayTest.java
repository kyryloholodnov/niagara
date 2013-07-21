package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.RemoveDuplicatesFromArray.removeDuplicates;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=22360665">http://www.careercup.com/question?id=22360665</a>
 * 
 * @author Kyrylo Holodnov
 */
public class RemoveDuplicatesFromArrayTest {
    @Test
    public void testRemoveDuplicatesWithNullArray() {
	int[] sequence = null;
	int[] res = removeDuplicates(sequence);
	assertNull("Result should be null", res);
    }

    @Test
    public void testRemoveDuplicatesWithZeroArray() {
	int[] sequence = new int[0];
	int[] res = removeDuplicates(sequence);
	assertThat("Result should be an empty array", res, is(new int[0]));
    }

    @Test
    public void testRemoveDuplicates1() {
	int[] sequence = new int[] { 5 };
	int[] res = removeDuplicates(sequence);
	assertThat("Result is not matched", res, is(new int[] { 5 }));
    }

    @Test
    public void testRemoveDuplicates2() {
	int[] sequence = new int[] { 1, 2, 3, 2, 1 };
	int[] res = removeDuplicates(sequence);
	assertThat("Result is not matched", res, is(new int[] { 1, 2, 3 }));
    }

    @Test
    public void testRemoveDuplicates3() {
	int[] sequence = new int[] { 3, 2, 4, 3, 5, 4 };
	int[] res = removeDuplicates(sequence);
	assertThat("Result is not matched", res, is(new int[] { 3, 2, 4, 5 }));
    }

    @Test
    public void testRemoveDuplicates4() {
	int[] sequence = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	int[] res = removeDuplicates(sequence);
	assertThat("Result is not matched", res, is(new int[] { 1, 2, 3, 4, 5,
		6, 7, 8, 9, 10 }));
    }

    @Test
    public void testRemoveDuplicates5() {
	int[] sequence = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 8, 9, 10 };
	int[] res = removeDuplicates(sequence);
	assertThat("Result is not matched", res, is(new int[] { 1, 2, 3, 4, 5,
		6, 7, 8, 9, 10 }));
    }

    @Test
    public void testRemoveDuplicates6() {
	int[] sequence = new int[] { 5, 5, 5, 5, 5 };
	int[] res = removeDuplicates(sequence);
	assertThat("Result is not matched", res, is(new int[] { 5 }));
    }

    @Test
    public void testRemoveDuplicates7() {
	int[] sequence = new int[] { 5, 5, 5, 5, 5, 1, 1 };
	int[] res = removeDuplicates(sequence);
	assertThat("Result is not matched", res, is(new int[] { 5, 1 }));
    }
}
