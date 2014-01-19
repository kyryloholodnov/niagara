package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.SequenceOfDivisibleNumbers.getNthNumber;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=20810664">http://www.careercup.com/question?id=20810664</a>
 * 
 * @author Kyrylo Holodnov
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
	assertThat(getNthNumber(1), is(1));
    }

    @Test
    public void testGetNthNumberWithTwo() {
	assertThat(getNthNumber(2), is(2));
    }

    @Test
    public void testGetNthNumberWithThree() {
	assertThat(getNthNumber(3), is(3));
    }

    @Test
    public void testGetNthNumberWithFour() {
	assertThat(getNthNumber(4), is(4));
    }

    @Test
    public void testGetNthNumberWithFive() {
	assertThat(getNthNumber(5), is(5));
    }

    @Test
    public void testGetNthNumberWithSix() {
	assertThat(getNthNumber(6), is(6));
    }

    @Test
    public void testGetNthNumberWithSeven() {
	assertThat(getNthNumber(7), is(8));
    }

    @Test
    public void testGetNthNumberWithEight() {
	assertThat(getNthNumber(8), is(9));
    }

    @Test
    public void testGetNthNumberWithNine() {
	assertThat(getNthNumber(9), is(10));
    }

    @Test
    public void testGetNthNumberWithTen() {
	assertThat(getNthNumber(10), is(12));
    }

    @Test
    public void testGetNthNumberWithEleven() {
	assertThat(getNthNumber(11), is(15));
    }

    @Test
    public void testGetNthNumberWithTwelve() {
	assertThat(getNthNumber(12), is(16));
    }

    @Test
    public void testGetNthNumberWithThirteen() {
	assertThat(getNthNumber(13), is(18));
    }

    @Test
    public void testGetNthNumberWithFourteen() {
	assertThat(getNthNumber(14), is(20));
    }

    @Test
    public void testGetNthNumberWithFifteen() {
	assertThat(getNthNumber(15), is(24));
    }

}
