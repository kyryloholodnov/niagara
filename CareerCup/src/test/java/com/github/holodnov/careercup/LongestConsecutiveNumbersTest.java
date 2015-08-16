package com.github.holodnov.careercup;

import org.junit.Test;

import static com.github.holodnov.careercup.LongestConsecutiveNumbers.findLongestConsecutiveNumbers;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=19778663">http://www.careercup.com/question?id=19778663</a>
 */
public class LongestConsecutiveNumbersTest {

    @Test(expected = IllegalArgumentException.class)
    public void testFindLongestConsecutiveNumbersNullArray() {
        assertNull(findLongestConsecutiveNumbers(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindLongestConsecutiveNumbersEmptyArray() {
        assertNull(findLongestConsecutiveNumbers(new int[0]));
    }

    @Test
    public void testFindLongestConsecutiveNumbersOneElementArray() {
        assertThat(findLongestConsecutiveNumbers(new int[]{5}), is(new int[]{5}));
    }

    @Test
    public void testFindLongestConsecutiveNumbers1() {
        assertThat(findLongestConsecutiveNumbers(new int[]{10, 1}), is(new int[]{10}));
    }

    @Test
    public void testFindLongestConsecutiveNumbers2() {
        assertThat(findLongestConsecutiveNumbers(new int[]{10, 9}), is(new int[]{9, 10}));
    }

    @Test
    public void testFindLongestConsecutiveNumbers3() {
        assertThat(findLongestConsecutiveNumbers(new int[]{10, 8, 9}), is(new int[]{8, 9, 10}));
    }

    @Test
    public void testFindLongestConsecutiveNumbers4() {
        assertThat(findLongestConsecutiveNumbers(new int[]{8, 2, 3, 7, 4, 0}), is(new int[]{2, 3, 4}));
    }

    @Test
    public void testFindLongestConsecutiveNumbers5() {
        assertThat(findLongestConsecutiveNumbers(new int[]{2, 4, 7, 9, 8, 4}), is(new int[]{7, 8, 9}));
    }

    @Test
    public void testFindLongestConsecutiveNumbers6() {
        assertThat(findLongestConsecutiveNumbers(new int[]{0, 1, 5, 2, 5}), is(new int[]{0, 1, 2}));
    }

    @Test
    public void testFindLongestConsecutiveNumbers7() {
        assertThat(findLongestConsecutiveNumbers(new int[]{10, 8, 6, 4, 2}), is(new int[]{10}));
    }

    @Test
    public void testFindLongestConsecutiveNumbers8() {
        assertThat(findLongestConsecutiveNumbers(new int[]{100, 99, 97, 201, 200, 1}), is(new int[]{200, 201}));
    }
}
