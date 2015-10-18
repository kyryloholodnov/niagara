package com.github.holodnov.careercup;

import org.junit.Test;

import static com.github.holodnov.careercup.MultithreadedSumCalculation.getSum;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=5161719076749312">http://www.careercup.com/question?id=5161719076749312</a>
 */
public class MultithreadedSumCalculationTest {

    @Test(expected = IllegalArgumentException.class)
    public void testGetSumNullArray() {
        getSum(null, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSumNonPositiveThreads() {
        getSum(new long[]{1, 2}, 0);
    }

    @Test
    public void testGetSum() {
        assertThat(getSum(new long[0], 5), is(0L));
        assertThat(getSum(new long[]{2}, 5), is(2L));
        assertThat(getSum(new long[]{2, 3, 5}, 100), is(10L));
        long[] array = new long[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        for (int i = 1; i <= 100; i++) {
            assertThat(getSum(array, i), is(499500L));
        }
    }
}
