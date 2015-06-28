package com.github.holodnov.careercup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static com.github.holodnov.careercup.Bishop.findShortestWay;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=5598833467719680">http://www.careercup.com/question?id=5598833467719680</a>
 */
@RunWith(Parameterized.class)
public class BishopTest {

    private final String from;
    private final String to;
    private final int expected;

    public BishopTest(String from, String to, int expected) {
        this.from = from;
        this.to = to;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return asList(
                new Object[]{"a1", "a1", 0},
                new Object[]{"a1", "b2", 1},
                new Object[]{"b2", "a1", 1},
                new Object[]{"a1", "c1", 2},
                new Object[]{"c1", "a1", 2},
                new Object[]{"a1", "h8", 1},
                new Object[]{"h8", "a1", 1},
                new Object[]{"a1", "h7", -1},
                new Object[]{"h7", "a1", -1},
                new Object[]{"a2", "h8", -1},
                new Object[]{"h8", "a2", -1},
                new Object[]{"a2", "h7", 2},
                new Object[]{"h7", "a2", 2},
                new Object[]{"h8", "h8", 0});
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
    public void testFindShortestWay() {
        assertThat("Shortest way from " + from + " to " + to + " is not expected",
                findShortestWay(from, to), is(expected));
    }
}
