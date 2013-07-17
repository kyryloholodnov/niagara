package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.GlassesWithWater.getAmountOfWater;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=22191662">http://www.careercup.com/question?id=22191662</a>
 * 
 * @author Kyrylo Holodnov
 */
public class GlassesWithWaterTest {
    @Test(expected = IllegalArgumentException.class)
    public void testGetAmountOfWaterNegativeX() {
	getAmountOfWater(0, 0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAmountOfWaterColumnBiggerThanRow() {
	getAmountOfWater(0, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAmountOfWaterLevelNegative() {
	getAmountOfWater(-1, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAmountOfWaterColumnNegative() {
	getAmountOfWater(1, -1, 2);
    }

    @Test
    public void testGetAmountOfWater1() {
	double res = getAmountOfWater(0, 0, 2);
	assertThat("Bad result in test", res, is(1.0));
    }

    @Test
    public void testGetAmountOfWater2() {
	double res = getAmountOfWater(1, 0, 2);
	assertThat("Bad result in test", res, is(0.5));
    }

    @Test
    public void testGetAmountOfWater3() {
	double res = getAmountOfWater(1, 1, 2);
	assertThat("Bad result in test", res, is(0.5));
    }

    @Test
    public void testGetAmountOfWater4() {
	double res = getAmountOfWater(1, 0, 1);
	assertThat("Bad result in test", res, is(0.0));
    }

    @Test
    public void testGetAmountOfWater5() {
	double res = getAmountOfWater(2, 0, 2);
	assertThat("Bad result in test", res, is(0.0));
    }

    @Test
    public void testGetAmountOfWater6() {
	double res = getAmountOfWater(2, 0, 4);
	assertThat("Bad result in test", res, is(0.25));
    }

    @Test
    public void testGetAmountOfWater7() {
	double res = getAmountOfWater(2, 1, 4);
	assertThat("Bad result in test", res, is(0.5));
    }

    @Test
    public void testGetAmountOfWater8() {
	double res = getAmountOfWater(2, 2, 4);
	assertThat("Bad result in test", res, is(0.25));
    }
}
