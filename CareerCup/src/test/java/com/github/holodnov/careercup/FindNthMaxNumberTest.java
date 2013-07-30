package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.FindNthMaxNumber.findNthMaxNumberWithBinaryHeap;
import static com.github.holodnov.careercup.FindNthMaxNumber.findNthMaxNumberWithSelectionSearch;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=20967663">http://www.careercup.com/question?id=20967663</a>
 * 
 * @author Kyrylo Holodnov
 */
public class FindNthMaxNumberTest {
    @Test(expected = IllegalArgumentException.class)
    public void testfindNthMaxNumberWithSelectionSearchNullArray() {
	findNthMaxNumberWithSelectionSearch(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testfindNthMaxNumberWithSelectionSearchLowN() {
	findNthMaxNumberWithSelectionSearch(new int[] { 2, 1, 3, 4, 0 }, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testfindNthMaxNumberWithSelectionSearchHighN() {
	findNthMaxNumberWithSelectionSearch(new int[] { 2, 1, 3, 4, 0 }, 6);
    }

    @Test
    public void testFindNthMaxNumberWithSelectionSearch1() {
	assertThat(
		findNthMaxNumberWithSelectionSearch(new int[] { 2, 1, 3, 4, 0,
			-1, 7, 5 }, 1), is(7));
	assertThat(
		findNthMaxNumberWithSelectionSearch(new int[] { 2, 1, 3, 4, 0,
			-1, 7, 5 }, 2), is(5));
	assertThat(
		findNthMaxNumberWithSelectionSearch(new int[] { 2, 1, 3, 4, 0,
			-1, 7, 5 }, 3), is(4));
	assertThat(
		findNthMaxNumberWithSelectionSearch(new int[] { 2, 1, 3, 4, 0,
			-1, 7, 5 }, 4), is(3));
	assertThat(
		findNthMaxNumberWithSelectionSearch(new int[] { 2, 1, 3, 4, 0,
			-1, 7, 5 }, 5), is(2));
	assertThat(
		findNthMaxNumberWithSelectionSearch(new int[] { 2, 1, 3, 4, 0,
			-1, 7, 5 }, 6), is(1));
	assertThat(
		findNthMaxNumberWithSelectionSearch(new int[] { 2, 1, 3, 4, 0,
			-1, 7, 5 }, 7), is(0));
	assertThat(
		findNthMaxNumberWithSelectionSearch(new int[] { 2, 1, 3, 4, 0,
			-1, 7, 5 }, 8), is(-1));
    }

    @Test
    public void testFindNthMaxNumberWithSelectionSearch2() {
	assertThat(
		findNthMaxNumberWithSelectionSearch(new int[] { 2, -4, 5, 6, 0,
			7, -1, 10, 9 }, 1), is(10));
	assertThat(
		findNthMaxNumberWithSelectionSearch(new int[] { 2, -4, 5, 6, 0,
			7, -1, 10, 9 }, 2), is(9));
	assertThat(
		findNthMaxNumberWithSelectionSearch(new int[] { 2, -4, 5, 6, 0,
			7, -1, 10, 9 }, 3), is(7));
	assertThat(
		findNthMaxNumberWithSelectionSearch(new int[] { 2, -4, 5, 6, 0,
			7, -1, 10, 9 }, 4), is(6));
	assertThat(
		findNthMaxNumberWithSelectionSearch(new int[] { 2, -4, 5, 6, 0,
			7, -1, 10, 9 }, 5), is(5));
	assertThat(
		findNthMaxNumberWithSelectionSearch(new int[] { 2, -4, 5, 6, 0,
			7, -1, 10, 9 }, 6), is(2));
	assertThat(
		findNthMaxNumberWithSelectionSearch(new int[] { 2, -4, 5, 6, 0,
			7, -1, 10, 9 }, 7), is(0));
	assertThat(
		findNthMaxNumberWithSelectionSearch(new int[] { 2, -4, 5, 6, 0,
			7, -1, 10, 9 }, 8), is(-1));
	assertThat(
		findNthMaxNumberWithSelectionSearch(new int[] { 2, -4, 5, 6, 0,
			7, -1, 10, 9 }, 9), is(-4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNthMaxNumberWithBinaryHeapNullArray() {
	findNthMaxNumberWithBinaryHeap(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNthMaxNumberWithBinaryHeapLowN() {
	findNthMaxNumberWithBinaryHeap(new int[] { 2, 1, 3, 4, 0 }, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNthMaxNumberWithBinaryHeapHighN() {
	findNthMaxNumberWithBinaryHeap(new int[] { 2, 1, 3, 4, 0 }, 6);
    }

    @Test
    public void testFindNthMaxNumberWithBinaryHeap1() {
	assertThat(
		findNthMaxNumberWithBinaryHeap(new int[] { 2, 1, 3, 4, 0, -1,
			7, 5 }, 1), is(7));
	assertThat(
		findNthMaxNumberWithBinaryHeap(new int[] { 2, 1, 3, 4, 0, -1,
			7, 5 }, 2), is(5));
	assertThat(
		findNthMaxNumberWithBinaryHeap(new int[] { 2, 1, 3, 4, 0, -1,
			7, 5 }, 3), is(4));
	assertThat(
		findNthMaxNumberWithBinaryHeap(new int[] { 2, 1, 3, 4, 0, -1,
			7, 5 }, 4), is(3));
	assertThat(
		findNthMaxNumberWithBinaryHeap(new int[] { 2, 1, 3, 4, 0, -1,
			7, 5 }, 5), is(2));
	assertThat(
		findNthMaxNumberWithBinaryHeap(new int[] { 2, 1, 3, 4, 0, -1,
			7, 5 }, 6), is(1));
	assertThat(
		findNthMaxNumberWithBinaryHeap(new int[] { 2, 1, 3, 4, 0, -1,
			7, 5 }, 7), is(0));
	assertThat(
		findNthMaxNumberWithBinaryHeap(new int[] { 2, 1, 3, 4, 0, -1,
			7, 5 }, 8), is(-1));
    }

    @Test
    public void testFindNthMaxNumberWithBinaryHeap2() {
	assertThat(
		findNthMaxNumberWithBinaryHeap(new int[] { 2, -4, 5, 6, 0, 7,
			-1, 10, 9 }, 1), is(10));
	assertThat(
		findNthMaxNumberWithBinaryHeap(new int[] { 2, -4, 5, 6, 0, 7,
			-1, 10, 9 }, 2), is(9));
	assertThat(
		findNthMaxNumberWithBinaryHeap(new int[] { 2, -4, 5, 6, 0, 7,
			-1, 10, 9 }, 3), is(7));
	assertThat(
		findNthMaxNumberWithBinaryHeap(new int[] { 2, -4, 5, 6, 0, 7,
			-1, 10, 9 }, 4), is(6));
	assertThat(
		findNthMaxNumberWithBinaryHeap(new int[] { 2, -4, 5, 6, 0, 7,
			-1, 10, 9 }, 5), is(5));
	assertThat(
		findNthMaxNumberWithBinaryHeap(new int[] { 2, -4, 5, 6, 0, 7,
			-1, 10, 9 }, 6), is(2));
	assertThat(
		findNthMaxNumberWithBinaryHeap(new int[] { 2, -4, 5, 6, 0, 7,
			-1, 10, 9 }, 7), is(0));
	assertThat(
		findNthMaxNumberWithBinaryHeap(new int[] { 2, -4, 5, 6, 0, 7,
			-1, 10, 9 }, 8), is(-1));
	assertThat(
		findNthMaxNumberWithBinaryHeap(new int[] { 2, -4, 5, 6, 0, 7,
			-1, 10, 9 }, 9), is(-4));
    }
}
