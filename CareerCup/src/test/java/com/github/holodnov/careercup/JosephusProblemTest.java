package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.JosephusProblem.getLastPerson;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=5977321018228736">http://www.careercup.com/question?id=5977321018228736</a>
 * 
 * @author Kyrylo Holodnov
 */
public class JosephusProblemTest {
    @Test(expected = IllegalArgumentException.class)
    public void testGetLastPersonNonPositiveN() {
	getLastPerson(0, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLastPersonNonPositiveK() {
	getLastPerson(10, 0);
    }

    @Test
    public void testGetLastPerson1() {
	assertThat(getLastPerson(10, 1), is(9));
    }

    @Test
    public void testGetLastPerson2() {
	assertThat(getLastPerson(10, 2), is(4));
    }

    @Test
    public void testGetLastPerson3() {
	assertThat(getLastPerson(10, 3), is(3));
    }

    @Test
    public void testGetLastPerson4() {
	assertThat(getLastPerson(10, 4), is(4));
    }

    @Test
    public void testGetLastPerson5() {
	assertThat(getLastPerson(10, 5), is(2));
    }

    @Test
    public void testGetLastPerson6() {
	assertThat(getLastPerson(10, 6), is(2));
    }

    @Test
    public void testGetLastPerson7() {
	assertThat(getLastPerson(10, 7), is(8));
    }

    @Test
    public void testGetLastPerson8() {
	assertThat(getLastPerson(10, 8), is(0));
    }

    @Test
    public void testGetLastPerson9() {
	assertThat(getLastPerson(10, 9), is(6));
    }

    @Test
    public void testGetLastPerson10() {
	assertThat(getLastPerson(10, 10), is(7));
    }
}
