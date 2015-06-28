package com.github.holodnov.careercup;

import org.junit.Test;

import java.util.Arrays;

import static com.github.holodnov.careercup.RadixSort.sort;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 */
public class RadixSortTest {

    @Test
    public void testSortForIntsNullArray() {
        int[] sequence = null;
        sort(sequence);
        assertNull("Sorting without exceptions, sequence should be null", sequence);
    }

    @Test
    public void testSortForLongsNullArray() {
        long[] sequence = null;
        sort(sequence);
        assertNull("Sorting without exceptions, sequence should be null", sequence);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSortForIntsNonPositiveArray() {
        int[] sequence = new int[]{1, 2, 3, 2, 1, -1, 2, 3, 5};
        sort(sequence);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSortForLongsNonPositiveArray() {
        long[] sequence = new long[]{1, 2, 3, 2, 1, -1, 2, 3, 5};
        sort(sequence);
    }

    @Test
    public void testSortForInts1() {
        int[] sequence = new int[]{5, 4, 1001, 100001, 32, 543, 55789, 432, 77778, 2013};
        sort(sequence);
        assertThat("Sort does not work for array = " + Arrays.toString(sequence),
                sequence, is(new int[]{4, 5, 32, 432, 543, 1001, 2013, 55789, 77778, 100001}));
    }

    @Test
    public void testSortForLongs1() {
        long[] sequence = new long[]{5, 4, 1001, 100001, 32, 543, 55789, 432, 77778, 2013};
        sort(sequence);
        assertThat("Sort does not work for array = " + Arrays.toString(sequence),
                sequence, is(new long[]{4, 5, 32, 432, 543, 1001, 2013, 55789, 77778, 100001}));
    }

    @Test
    public void testSortForInts2() {
        int[] sequence = new int[]{43235, 32452, 45674, 23, 1, 547, 11, 43554645, 2354232};
        sort(sequence);
        assertThat("Sort does not work for array = " + Arrays.toString(sequence),
                sequence, is(new int[]{1, 11, 23, 547, 32452, 43235, 45674, 2354232, 43554645}));
    }

    @Test
    public void testSortForLongs2() {
        long[] sequence = new long[]{43235, 32452, 45674, 23, 1, 547, 11, 43554645, 2354232};
        sort(sequence);
        assertThat("Sort does not work for array = " + Arrays.toString(sequence),
                sequence, is(new long[]{1, 11, 23, 547, 32452, 43235, 45674, 2354232, 43554645}));
    }
}
