package com.github.holodnov.careercup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static com.github.holodnov.careercup.MovingSpacesToStartOfString.moveSpaces;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=5242643239927808">http://www.careercup.com/question?id=5242643239927808</a>
 */
@RunWith(Parameterized.class)
public class MovingSpacesToStartOfStringTest {

    private final char[] str;
    private final char[] expected;

    public MovingSpacesToStartOfStringTest(String str, String expected) {
        this.str = str == null ? null : str.toCharArray();
        this.expected = expected == null ? null : expected.toCharArray();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return asList(
                new Object[]{null, null},
                new Object[]{"", ""},
                new Object[]{" ", " "},
                new Object[]{"  ", "  "},
                new Object[]{"a a", " aa"},
                new Object[]{" a b c d  e", "      abcde"},
                new Object[]{"     x", "     x"},
                new Object[]{"     x ", "      x"},
                new Object[]{"abcdefgh ", " abcdefgh"},
                new Object[]{" abcdefgh ", "  abcdefgh"},
                new Object[]{"abcdefgh", "abcdefgh"},
                new Object[]{"abcd efgh", " abcdefgh"}
        );
    }

    @Test
    public void testMoveSpaces() {
        String original = str == null ? null : new String(str);
        moveSpaces(str);
        assertThat("Result for character array of string = " + original + " is not expected",
                str,
                is(expected));
    }
}
