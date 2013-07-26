package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.TopKNumbersFromThreeSortedArrays.findTopKNumbersFromThreeSortedArrays;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=23203670">http://www.careercup.com/question?id=23203670</a>
 * 
 * @author Kyrylo Holodnov
 */
public class TopKNumbersFromThreeSortedArraysTest {
    @Test(expected = IllegalArgumentException.class)
    public void testFindTopKNumbersFromThreeSortedArraysFirstIsNull() {
	findTopKNumbersFromThreeSortedArrays(null, new int[] { 1 },
		new int[] { 2 }, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTopKNumbersFromThreeSortedArraysSecondIsNull() {
	findTopKNumbersFromThreeSortedArrays(new int[] { 1 }, null,
		new int[] { 2 }, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTopKNumbersFromThreeSortedArraysThirdIsNull() {
	findTopKNumbersFromThreeSortedArrays(new int[] { 1 }, new int[] { 2 },
		null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTopKNumbersFromThreeSortedArraysKIsBig() {
	findTopKNumbersFromThreeSortedArrays(new int[] { 1, 2 }, new int[] { 1,
		3 }, new int[] { 1, 4 }, 7);
    }

    @Test
    public void testFindTopKNumbersFromThreeSorted1() {
	int[] res = findTopKNumbersFromThreeSortedArrays(new int[] { 1, 2 },
		new int[] { 1, 3 }, new int[] { 1, 4 }, 1);
	assertThat(res, is(new int[] { 4 }));
    }

    @Test
    public void testFindTopKNumbersFromThreeSorted2() {
	int[] res = findTopKNumbersFromThreeSortedArrays(new int[] { 1, 2 },
		new int[] { 1, 3 }, new int[] { 1, 4 }, 3);
	assertThat(res, is(new int[] { 2, 3, 4 }));
    }

    @Test
    public void testFindTopKNumbersFromThreeSorted3() {
	int[] res = findTopKNumbersFromThreeSortedArrays(new int[] { 1, 2, 7 },
		new int[] { 1, 3, 4 }, new int[] { 1, 4, 5 }, 5);
	assertThat(res, is(new int[] { 3, 4, 4, 5, 7 }));
    }

    @Test
    public void testFindTopKNumbersFromThreeSorted4() {
	int[] res = findTopKNumbersFromThreeSortedArrays(new int[] { 1, 2, 2,
		2, 7 }, new int[] { 5 }, new int[] { 1 }, 4);
	assertThat(res, is(new int[] { 2, 2, 5, 7 }));
    }

    @Test
    public void testFindTopKNumbersFromThreeSorted5() {
	int[] res = findTopKNumbersFromThreeSortedArrays(new int[] { 1, 2, 2,
		2, 7 }, new int[] { 5, 7 },
		new int[] { 1, 2, 3, 4, 5, 6, 7, 8 }, 8);
	assertThat(res, is(new int[] { 4, 5, 5, 6, 7, 7, 7, 8 }));
    }
}
