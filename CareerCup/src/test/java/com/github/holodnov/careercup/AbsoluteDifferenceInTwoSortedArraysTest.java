package com.github.holodnov.careercup;

import com.github.holodnov.careercup.AbsoluteDifferenceInTwoSortedArrays.Triple;
import org.junit.Test;

import static com.github.holodnov.careercup.AbsoluteDifferenceInTwoSortedArrays.findMinimumDifference;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=22808663">http://www.careercup.com/question?id=22808663</a>
 */
public class AbsoluteDifferenceInTwoSortedArraysTest {

    @Test(expected = IllegalArgumentException.class)
    public void testFindMinimumDifferenceFirstIsNull() {
        findMinimumDifference(null, new int[]{1, 2, 3});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindMinimumDifferenceSecondIsNull() {
        findMinimumDifference(new int[]{1, 2, 3}, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindMinimumDifferenceBothAreNull() {
        findMinimumDifference(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindMinimumDifferenceDifferentLength() {
        findMinimumDifference(new int[]{1, 2, 3, 4}, new int[]{2, 4, 5});
    }

    @Test
    public void testFindMinimumDifference1() {
        Triple res = findMinimumDifference(new int[]{1}, new int[]{2});
        assertThat(res, is(new Triple(1, 0, 0)));
    }

    @Test
    public void testFindMinimumDifference2() {
        Triple res = findMinimumDifference(new int[]{1, 2},
                new int[]{2, 3});
        assertThat(res, is(new Triple(0, 1, 0)));
    }

    @Test
    public void testFindMinimumDifference3() {
        Triple res = findMinimumDifference(new int[]{1, 5, 10, 17},
                new int[]{18, 19, 25, 27});
        assertThat(res, is(new Triple(1, 3, 0)));
    }

    @Test
    public void testFindMinimumDifference4() {
        Triple res = findMinimumDifference(new int[]{1, 2, 3, 4},
                new int[]{2, 4, 5, 7});
        assertThat(res, is(new Triple(0, 1, 0)));
    }
}
