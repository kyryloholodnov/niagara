package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.Anagram.isAnagram;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=5136177443110912">http://www.careercup.com/question?id=5136177443110912</a>
 * 
 * @author Kyrylo Holodnov
 */
public class AnagramTest {

    private static final char NON_ASCII_CHAR = 'a' + 256;

    @Test(expected = IllegalArgumentException.class)
    public void testIsAnagramNullFirstArgument() {
	isAnagram(null, "abc");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsAnagramNullSecondArgument() {
	isAnagram("abc", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsAnagramFirstArgumentNonASCII() {
	isAnagram("abc" + NON_ASCII_CHAR, "abcd");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsAnagramSecondArgumentNonASCII() {
	isAnagram("abcd", "abc" + NON_ASCII_CHAR);
    }

    @Test
    public void testIsAnagramDifferentLenght() {
	assertFalse(isAnagram("abcd", "abcde"));
    }

    @Test
    public void testIsAnagram1() {
	assertFalse(isAnagram("abcd", "abcde"));
    }

    @Test
    public void testIsAnagram2() {
	assertFalse(isAnagram("abcd", "xbcd"));
    }

    @Test
    public void testIsAnagram3() {
	assertFalse(isAnagram("abcd", "abCd"));
    }

    @Test
    public void testIsAnagram4() {
	assertTrue(isAnagram("abcda", "aabdc"));
    }

    @Test
    public void testIsAnagram5() {
	assertTrue(isAnagram("abcdaxzx", "axabzdcx"));
    }

    @Test
    public void testIsAnagram6() {
	assertFalse(isAnagram("abc123daxzxx", "213axabzdcx1"));
    }

    @Test
    public void testIsAnagram7() {
	assertTrue(isAnagram("" + (char) (0) + (char) (255), "" + (char) (0)
		+ (char) (255)));
    }
}
