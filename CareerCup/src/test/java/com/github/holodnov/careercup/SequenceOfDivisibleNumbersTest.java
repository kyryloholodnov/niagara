package com.github.holodnov.careercup;

import org.junit.Test;

import static com.github.holodnov.careercup.SequenceOfDivisibleNumbers.getNthNumber;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=20810664">http://www.careercup.com/question?id=20810664</a>
 */
public class SequenceOfDivisibleNumbersTest {

    @Test(expected = IllegalArgumentException.class)
    public void testGetNthNumberNegativeInput() {
        getNthNumber(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNthNumberZeroInput() {
        getNthNumber(0);
    }

    @Test
    public void testGetNthNumberWithOne() {
        assertThat(getNthNumber(1), is(1L));
    }

    @Test
    public void testGetNthNumberWithTwo() {
        assertThat(getNthNumber(2), is(2L));
    }

    @Test
    public void testGetNthNumberWithThree() {
        assertThat(getNthNumber(3), is(3L));
    }

    @Test
    public void testGetNthNumberWithFour() {
        assertThat(getNthNumber(4), is(4L));
    }

    @Test
    public void testGetNthNumberWithFive() {
        assertThat(getNthNumber(5), is(5L));
    }

    @Test
    public void testGetNthNumberWithSix() {
        assertThat(getNthNumber(6), is(6L));
    }

    @Test
    public void testGetNthNumberWithSeven() {
        assertThat(getNthNumber(7), is(8L));
    }

    @Test
    public void testGetNthNumberWithEight() {
        assertThat(getNthNumber(8), is(9L));
    }

    @Test
    public void testGetNthNumberWithNine() {
        assertThat(getNthNumber(9), is(10L));
    }

    @Test
    public void testGetNthNumberWithTen() {
        assertThat(getNthNumber(10), is(12L));
    }

    @Test
    public void testGetNthNumberWithEleven() {
        assertThat(getNthNumber(11), is(15L));
    }

    @Test
    public void testGetNthNumberWithTwelve() {
        assertThat(getNthNumber(12), is(16L));
    }

    @Test
    public void testGetNthNumberWithThirteen() {
        assertThat(getNthNumber(13), is(18L));
    }

    @Test
    public void testGetNthNumberWithFourteen() {
        assertThat(getNthNumber(14), is(20L));
    }

    @Test
    public void testGetNthNumberWithFifteen() {
        assertThat(getNthNumber(15), is(24L));
    }
}
