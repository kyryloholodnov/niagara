package com.github.holodnov.careercup;

import org.junit.Test;

import static com.github.holodnov.careercup.GlassesWithWater.getAmountOfWater;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=22191662">http://www.careercup.com/question?id=22191662</a>
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
        assertThat(getAmountOfWater(0, 0, 2), is(1.0));
    }

    @Test
    public void testGetAmountOfWater2() {
        assertThat(getAmountOfWater(1, 0, 2), is(0.5));
    }

    @Test
    public void testGetAmountOfWater3() {
        assertThat(getAmountOfWater(1, 1, 2), is(0.5));
    }

    @Test
    public void testGetAmountOfWater4() {
        assertThat(getAmountOfWater(1, 0, 1), is(0.0));
    }

    @Test
    public void testGetAmountOfWater5() {
        assertThat(getAmountOfWater(2, 0, 2), is(0.0));
    }

    @Test
    public void testGetAmountOfWater6() {
        assertThat(getAmountOfWater(2, 0, 4), is(0.25));
    }

    @Test
    public void testGetAmountOfWater7() {
        assertThat(getAmountOfWater(2, 1, 4), is(0.5));
    }

    @Test
    public void testGetAmountOfWater8() {
        assertThat(getAmountOfWater(2, 2, 4), is(0.25));
    }
}
