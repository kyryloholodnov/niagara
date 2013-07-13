package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.MovingSpacesToStartOfString.moveSpaces;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=5242643239927808">http://www.careercup.com/question?id=5242643239927808</a>
 * 
 * @author Kyrylo Holodnov
 */
@RunWith(Parameterized.class)
public class MovingSpacesToStartOfStringTest {

    private char[] str;
    private char[] expected;

    public MovingSpacesToStartOfStringTest(String str, String expected) {
	this.str = (str == null) ? null : str.toCharArray();
	this.expected = (expected == null) ? null : expected.toCharArray();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
	return Arrays.asList(new Object[] { null, null },
		new Object[] { "", "" }, new Object[] { " ", " " },
		new Object[] { "  ", "  " }, new Object[] { "a a", " aa" },
		new Object[] { " a b c d  e", "      abcde" }, new Object[] {
			"     x", "     x" }, new Object[] { "     x ",
			"      x" }, new Object[] { "abcdefgh ", " abcdefgh" },
		new Object[] { " abcdefgh ", "  abcdefgh" }, new Object[] {
			"abcdefgh", "abcdefgh" }, new Object[] { "abcd efgh",
			" abcdefgh" });
    }

    @Test
    public void testMoveSpaces() {
	String original = (str == null) ? null : new String(str);
	moveSpaces(str);
	assertThat("Result for character array of string = \"" + original
		+ "\" is not expected", expected, is(str));
    }
}
