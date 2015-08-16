package com.github.holodnov.careercup;

import org.junit.Test;

import static com.github.holodnov.careercup.LeastDifferenceInArrayWithGivenNumber.getElementWithLeastDifference;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=20982670">http://www.careercup.com/question?id=20982670</a>
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
        int actual = getElementWithLeastDifference(new int[]{1, 3, 5, 7, 9, 11, 13, 15}, 1);
        assertThat(actual, is(1));
    }

    @Test
    public void testGetElementWithLeastDifference2() {
        int actual = getElementWithLeastDifference(new int[]{1, 4, 5, 7, 9, 11, 13, 15}, 2);
        assertThat(actual, is(1));
    }

    @Test
    public void testGetElementWithLeastDifference3() {
        int actual = getElementWithLeastDifference(new int[]{1, 3, 5, 7, 9, 11, 13, 15}, 2);
        assertThat(actual, is(1));
    }

    @Test
    public void testGetElementWithLeastDifference4() {
        int actual = getElementWithLeastDifference(new int[]{1, 4, 5, 7, 9, 11, 13, 15}, 2);
        assertThat(actual, is(1));
    }

    @Test
    public void testGetElementWithLeastDifference5() {
        int actual = getElementWithLeastDifference(new int[]{1, 4, 5, 7, 9, 11, 13, 15}, 5);
        assertThat(actual, is(5));
    }

    @Test
    public void testGetElementWithLeastDifference6() {
        int actual = getElementWithLeastDifference(new int[]{1, 4, 5, 7, 9, 11, 13, 15}, 14);
        assertThat(actual, is(13));
    }

    @Test
    public void testGetElementWithLeastDifference7() {
        int actual = getElementWithLeastDifference(new int[]{1, 4, 5, 7, 9, 11, 13, 15}, 15);
        assertThat(actual, is(15));
    }

    @Test
    public void testGetElementWithLeastDifference8() {
        int actual = getElementWithLeastDifference(new int[]{1, 4, 5, 7, 9, 11, 13, 15}, 20);
        assertThat(actual, is(15));
    }
}
