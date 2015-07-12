package com.github.holodnov.careercup;

import org.junit.Test;

import static com.github.holodnov.careercup.FindNthMaxNumber.findNthMaxNumberUsingBinaryHeap;
import static com.github.holodnov.careercup.FindNthMaxNumber.findNthMaxNumberUsingSelectionSearch;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=20967663">http://www.careercup.com/question?id=20967663</a>
 */
public class FindNthMaxNumberTest {

    @Test(expected = IllegalArgumentException.class)
    public void testFindNthMaxNumberUsingSelectionSearchNullArray() {
        findNthMaxNumberUsingSelectionSearch(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNthMaxNumberUsingSelectionSearchLowN() {
        findNthMaxNumberUsingSelectionSearch(new int[]{2, 1, 3, 4, 0}, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNthMaxNumberUsingSelectionSearchHighN() {
        findNthMaxNumberUsingSelectionSearch(new int[]{2, 1, 3, 4, 0}, 6);
    }

    @Test
    public void testFindNthMaxNumberUsingSelectionSearch1() {
        int[] sequence = new int[]{2, 1, 3, 4, 0, -1, 7, 5};
        assertThat(findNthMaxNumberUsingSelectionSearch(sequence, 1), is(7));
        assertThat(findNthMaxNumberUsingSelectionSearch(sequence, 2), is(5));
        assertThat(findNthMaxNumberUsingSelectionSearch(sequence, 3), is(4));
        assertThat(findNthMaxNumberUsingSelectionSearch(sequence, 4), is(3));
        assertThat(findNthMaxNumberUsingSelectionSearch(sequence, 5), is(2));
        assertThat(findNthMaxNumberUsingSelectionSearch(sequence, 6), is(1));
        assertThat(findNthMaxNumberUsingSelectionSearch(sequence, 7), is(0));
        assertThat(findNthMaxNumberUsingSelectionSearch(sequence, 8), is(-1));
    }

    @Test
    public void testFindNthMaxNumberUsingSelectionSearch2() {
        int[] sequence = new int[]{2, -4, 5, 6, 0, 7, -1, 10, 9};
        assertThat(findNthMaxNumberUsingSelectionSearch(sequence, 1), is(10));
        assertThat(findNthMaxNumberUsingSelectionSearch(sequence, 2), is(9));
        assertThat(findNthMaxNumberUsingSelectionSearch(sequence, 3), is(7));
        assertThat(findNthMaxNumberUsingSelectionSearch(sequence, 4), is(6));
        assertThat(findNthMaxNumberUsingSelectionSearch(sequence, 5), is(5));
        assertThat(findNthMaxNumberUsingSelectionSearch(sequence, 6), is(2));
        assertThat(findNthMaxNumberUsingSelectionSearch(sequence, 7), is(0));
        assertThat(findNthMaxNumberUsingSelectionSearch(sequence, 8), is(-1));
        assertThat(findNthMaxNumberUsingSelectionSearch(sequence, 9), is(-4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNthMaxNumberUsingBinaryHeapNullArray() {
        findNthMaxNumberUsingBinaryHeap(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNthMaxNumberUsingBinaryHeapLowN() {
        findNthMaxNumberUsingBinaryHeap(new int[]{2, 1, 3, 4, 0}, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNthMaxNumberUsingBinaryHeapHighN() {
        findNthMaxNumberUsingBinaryHeap(new int[]{2, 1, 3, 4, 0}, 6);
    }

    @Test
    public void testFindNthMaxNumberUsingBinaryHeap1() {
        int[] sequence = new int[]{2, 1, 3, 4, 0, -1, 7, 5};
        assertThat(findNthMaxNumberUsingBinaryHeap(sequence, 1), is(7));
        assertThat(findNthMaxNumberUsingBinaryHeap(sequence, 2), is(5));
        assertThat(findNthMaxNumberUsingBinaryHeap(sequence, 3), is(4));
        assertThat(findNthMaxNumberUsingBinaryHeap(sequence, 4), is(3));
        assertThat(findNthMaxNumberUsingBinaryHeap(sequence, 5), is(2));
        assertThat(findNthMaxNumberUsingBinaryHeap(sequence, 6), is(1));
        assertThat(findNthMaxNumberUsingBinaryHeap(sequence, 7), is(0));
        assertThat(findNthMaxNumberUsingBinaryHeap(sequence, 8), is(-1));
    }

    @Test
    public void testFindNthMaxNumberUsingBinaryHeap2() {
        int[] sequence = new int[]{2, -4, 5, 6, 0, 7, -1, 10, 9};
        assertThat(findNthMaxNumberUsingBinaryHeap(sequence, 1), is(10));
        assertThat(findNthMaxNumberUsingBinaryHeap(sequence, 2), is(9));
        assertThat(findNthMaxNumberUsingBinaryHeap(sequence, 3), is(7));
        assertThat(findNthMaxNumberUsingBinaryHeap(sequence, 4), is(6));
        assertThat(findNthMaxNumberUsingBinaryHeap(sequence, 5), is(5));
        assertThat(findNthMaxNumberUsingBinaryHeap(sequence, 6), is(2));
        assertThat(findNthMaxNumberUsingBinaryHeap(sequence, 7), is(0));
        assertThat(findNthMaxNumberUsingBinaryHeap(sequence, 8), is(-1));
        assertThat(findNthMaxNumberUsingBinaryHeap(sequence, 9), is(-4));
    }
}
