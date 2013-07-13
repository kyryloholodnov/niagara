package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.Bishop.findShortestWay;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=5598833467719680">http://www.careercup.com/question?id=5598833467719680</a>
 * 
 * @author Kyrylo Holodnov
 */
@RunWith(Parameterized.class)
public class BishopTest {

    private String from;
    private String to;
    private int expected;

    public BishopTest(String from, String to, int expected) {
	this.from = from;
	this.to = to;
	this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
	return Arrays.asList(new Object[] { "a1", "b3", 1 }, new Object[] {
		"b3", "a1", 1 }, new Object[] { "a1", "c1", 2 }, new Object[] {
		"c1", "a1", 2 }, new Object[] { "a1", "h8", 6 }, new Object[] {
		"h8", "a1", 6 }, new Object[] { "a1", "h7", 5 }, new Object[] {
		"h7", "a1", 5 }, new Object[] { "a2", "h8", 5 }, new Object[] {
		"h8", "a2", 5 }, new Object[] { "a2", "h7", 4 }, new Object[] {
		"h7", "a2", 4 });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindShortestWayStartIsNull() {
	findShortestWay(null, "h8");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindShortestWayStartIsEmpty() {
	findShortestWay("", "h8");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindShortestWayStartTooShort() {
	findShortestWay("a", "h8");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindShortestWayStartTooLong() {
	findShortestWay("a11", "h8");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindShortestWayEndIsNull() {
	findShortestWay("a1", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindShortestWayEndIsEmpty() {
	findShortestWay("a1", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindShortestWayEndTooShort() {
	findShortestWay("a1", "h");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindShortestWayEndTooLong() {
	findShortestWay("a11", "h88");
    }

    @Test
    public void testFindShortestWayA1B3() {
	int res = findShortestWay(from, to);
	assertThat("Shortest way from " + from + " to " + to
		+ " is not expected", res, is(expected));
    }

}
