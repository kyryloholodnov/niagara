package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.LongestConsecutiveNumbers.findLongestConsecutiveNumbers;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=19778663">http://www.careercup.com/question?id=19778663</a>
 * 
 * @author Kyrylo Holodnov
 */
public class LongestConsecutiveNumbersTest {
    @Test(expected = IllegalArgumentException.class)
    public void testFindLongestConsecutiveNumbersNullArray() {
	int[] res = findLongestConsecutiveNumbers(null);
	assertNull(res);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindLongestConsecutiveNumbersEmptyArray() {
	int[] res = findLongestConsecutiveNumbers(new int[0]);
	assertNull(res);
    }

    @Test
    public void testFindLongestConsecutiveNumbersOneElementArray() {
	int[] res = findLongestConsecutiveNumbers(new int[] { 5 });
	assertThat(res, is(new int[] { 5 }));
    }

    @Test
    public void testFindLongestConsecutiveNumbers1() {
	int[] res = findLongestConsecutiveNumbers(new int[] { 10, 1 });
	assertThat(res, is(new int[] { 10 }));
    }

    @Test
    public void testFindLongestConsecutiveNumbers2() {
	int[] res = findLongestConsecutiveNumbers(new int[] { 10, 9 });
	assertThat(res, is(new int[] { 9, 10 }));
    }

    @Test
    public void testFindLongestConsecutiveNumbers3() {
	int[] res = findLongestConsecutiveNumbers(new int[] { 10, 8, 9 });
	assertThat(res, is(new int[] { 8, 9, 10 }));
    }

    @Test
    public void testFindLongestConsecutiveNumbers4() {
	int[] res = findLongestConsecutiveNumbers(new int[] { 8, 2, 3, 7, 4, 0 });
	assertThat(res, is(new int[] { 2, 3, 4 }));
    }

    @Test
    public void testFindLongestConsecutiveNumbers5() {
	int[] res = findLongestConsecutiveNumbers(new int[] { 2, 4, 7, 9, 8, 4 });
	assertThat(res, is(new int[] { 7, 8, 9 }));
    }

    @Test
    public void testFindLongestConsecutiveNumbers6() {
	int[] res = findLongestConsecutiveNumbers(new int[] { 0, 1, 5, 2, 5 });
	assertThat(res, is(new int[] { 0, 1, 2 }));
    }

    @Test
    public void testFindLongestConsecutiveNumbers7() {
	int[] res = findLongestConsecutiveNumbers(new int[] { 10, 8, 6, 4, 2 });
	assertThat(res, is(new int[] { 10 }));
    }

    @Test
    public void testFindLongestConsecutiveNumbers8() {
	int[] res = findLongestConsecutiveNumbers(new int[] { 100, 99, 97, 201,
		200, 1 });
	assertThat(res, is(new int[] { 200, 201 }));
    }
}
