package com.github.holodnov.careercup;

import org.junit.Test;

import static com.github.holodnov.careercup.SequenceOfDivisibleNumbers.getNthNumber;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.valueOf;
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
        assertThat(getNthNumber(1), is(ONE));
    }

    @Test
    public void testGetNthNumberWithTwo() {
        assertThat(getNthNumber(2), is(valueOf(2)));
    }

    @Test
    public void testGetNthNumberWithThree() {
        assertThat(getNthNumber(3), is(valueOf(3)));
    }

    @Test
    public void testGetNthNumberWithFour() {
        assertThat(getNthNumber(4), is(valueOf(4)));
    }

    @Test
    public void testGetNthNumberWithFive() {
        assertThat(getNthNumber(5), is(valueOf(5)));
    }

    @Test
    public void testGetNthNumberWithSix() {
        assertThat(getNthNumber(6), is(valueOf(6)));
    }

    @Test
    public void testGetNthNumberWithSeven() {
        assertThat(getNthNumber(7), is(valueOf(8)));
    }

    @Test
    public void testGetNthNumberWithEight() {
        assertThat(getNthNumber(8), is(valueOf(9)));
    }

    @Test
    public void testGetNthNumberWithNine() {
        assertThat(getNthNumber(9), is(valueOf(10)));
    }

    @Test
    public void testGetNthNumberWithTen() {
        assertThat(getNthNumber(10), is(valueOf(12)));
    }

    @Test
    public void testGetNthNumberWithEleven() {
        assertThat(getNthNumber(11), is(valueOf(15)));
    }

    @Test
    public void testGetNthNumberWithTwelve() {
        assertThat(getNthNumber(12), is(valueOf(16)));
    }

    @Test
    public void testGetNthNumberWithThirteen() {
        assertThat(getNthNumber(13), is(valueOf(18)));
    }

    @Test
    public void testGetNthNumberWithFourteen() {
        assertThat(getNthNumber(14), is(valueOf(20)));
    }

    @Test
    public void testGetNthNumberWithFifteen() {
        assertThat(getNthNumber(15), is(valueOf(24)));
    }
}
