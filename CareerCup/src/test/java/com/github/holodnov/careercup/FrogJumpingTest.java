package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.FrogJumping.findShortestWay;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=24669663">http://www.careercup.com/question?id=24669663</a>
 * 
 * @author Kyrylo Holodnov
 */
public class FrogJumpingTest {
    @Test(expected = IllegalArgumentException.class)
    public void testFindShortestWayInputArrayIsNull() {
	findShortestWay(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindShortestWayInputArrayIsEmpty() {
	findShortestWay(new int[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindShortestWayInputArrayWithNegativeElements() {
	findShortestWay(new int[] { 1, 2, 3, 2, 9, -1, 5, 7 });
    }

    @Test
    public void testFindShortestWay1() {
	assertThat(findShortestWay(new int[] { 1, 5, 4, 6, 9, 3, 0, 0, 1, 3 }),
		is(3));
    }

    @Test
    public void testFindShortestWay2() {
	assertThat(findShortestWay(new int[] { 1, 5, 4, 6, 9, 3, 0, 0, 0, 0 }),
		is(3));
    }

    @Test
    public void testFindShortestWay3() {
	assertThat(findShortestWay(new int[] { 1, 5, 4, 6, 5, 3, 0, 0, 0, 0 }),
		is(Integer.MAX_VALUE));
    }

    @Test
    public void testFindShortestWay4() {
	assertThat(findShortestWay(new int[] { 0, 5, 4, 6, 9, 3, 0, 0, 1, 3 }),
		is(Integer.MAX_VALUE));
    }

    @Test
    public void testFindShortestWay5() {
	assertThat(findShortestWay(new int[] { 1, 0, 4, 6, 9, 3, 0, 0, 1, 3 }),
		is(Integer.MAX_VALUE));
    }

    @Test
    public void testFindShortestWay6() {
	assertThat(findShortestWay(new int[] { 2, 8, 3, 6, 9, 3, 0, 0, 1, 3 }),
		is(3));
    }

    @Test
    public void testFindShortestWay7() {
	assertThat(findShortestWay(new int[] { 2, 9, 3, 6, 9, 3, 0, 0, 1, 3 }),
		is(2));
    }
}
