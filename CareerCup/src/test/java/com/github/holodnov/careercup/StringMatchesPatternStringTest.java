package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.StringMatchesPatternString.matches;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=23806685">http://www.careercup.com/question?id=23806685</a>
 * 
 * @author Kyrylo Holodnov
 */
public class StringMatchesPatternStringTest {
    @Test(expected = IllegalArgumentException.class)
    public void testMatchesBothParamsNull() {
	matches(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMatchesInputStrNull() {
	matches(null, "abc*a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMatchesInputPatternNull() {
	matches("abc12", null);
    }

    @Test
    public void testMatchesBothParamsEmpty() {
	assertTrue(matches("", ""));
    }

    @Test
    public void testMatches1() {
	assertTrue(matches("", "*"));
	assertTrue(matches("", "**"));
	assertTrue(matches("", "***"));
	assertTrue(matches("a", "*"));
	assertTrue(matches("ab", "*"));
	assertTrue(matches("abc", "*"));
	assertTrue(matches("ab", "**"));
	assertTrue(matches("abc", "***"));
	assertTrue(matches("abc", "****"));
    }

    @Test
    public void testMatches2() {
	assertFalse(matches("", "*?"));
	assertTrue(matches("b", "*?"));
	assertTrue(matches("ba", "*?"));
	assertTrue(matches("cba", "*?"));
    }

    @Test
    public void testMatches3() {
	assertTrue(matches("abc", "a*c"));
	assertFalse(matches("abc", "a*b"));
	assertTrue(matches("abcdef", "abc?ef"));
	assertFalse(matches("abcdefa", "abc?ef"));
	assertTrue(matches("abcdef", "abc*ef"));
	assertFalse(matches("abcdefa", "abc*ef"));
    }

    @Test
    public void testMatches4() {
	assertTrue(matches("abcbca", "ab*cbca"));
	assertFalse(matches("abcbca", "ab?cbca"));
	assertTrue(matches("abcbca", "ab*?b*ca*"));
	assertTrue(matches("cb", "*?*b"));
	assertFalse(matches("b", "*?*b"));
    }

    @Test
    public void testMatches5() {
	assertTrue(matches("abcbca", "abcbca"));
	assertFalse(matches("abcbca", "abcbcab"));
	assertFalse(matches("abcbca", "abcbc"));
	assertTrue(matches("abcbca", "?bcbca"));
	assertFalse(matches("abcbca", "a?cbcab"));
	assertFalse(matches("abcbca", "abcb?"));
    }

}
