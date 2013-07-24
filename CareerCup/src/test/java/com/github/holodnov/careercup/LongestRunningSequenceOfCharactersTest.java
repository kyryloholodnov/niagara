package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.LongestRunningSequenceOfCharacters.getLongestRunningSequence;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.holodnov.careercup.LongestRunningSequenceOfCharacters.CharAndInteger;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=20235671">http://www.careercup.com/question?id=20235671</a>
 * 
 * @author Kyrylo Holodnov
 */
public class LongestRunningSequenceOfCharactersTest {

    @Test
    public void testGetLongestRunningSequenceNullArray() {
	CharAndInteger res = getLongestRunningSequence(null);
	assertNull(res);
    }

    @Test
    public void testGetLongestRunningSequenceEmptyArray() {
	CharAndInteger res = getLongestRunningSequence(new String[0]);
	assertNull(res);
    }

    @Test
    public void testGetLongestRunningSequenceArrayWithEmpties() {
	CharAndInteger res = getLongestRunningSequence(new String[] { "", null,
		null, "", "" });
	assertNull(res);
    }

    @Test
    public void testGetLongestRunningSequence1() {
	CharAndInteger res = getLongestRunningSequence(new String[] { "a",
		"ab", "bba" });
	assertThat(res, is(new CharAndInteger('b', 3)));
    }

    @Test
    public void testGetLongestRunningSequence2() {
	CharAndInteger res = getLongestRunningSequence(new String[] { "aaa",
		"ab", "bba" });
	assertThat(res, is(new CharAndInteger('a', 5)));
    }

    @Test
    public void testGetLongestRunningSequence3() {
	CharAndInteger res = getLongestRunningSequence(new String[] { "ab",
		"ba", "", null, "aac" });
	assertThat(res, is(new CharAndInteger('a', 3)));
    }

    @Test
    public void testGetLongestRunningSequence4() {
	CharAndInteger res = getLongestRunningSequence(new String[] { "aaaa",
		"aaa", "aa", "a" });
	assertThat(res, is(new CharAndInteger('a', 10)));
    }

    @Test
    public void testGetLongestRunningSequence5() {
	CharAndInteger res = getLongestRunningSequence(new String[] { "aaaa",
		"bbb", "ccccc", "d" });
	assertThat(res, is(new CharAndInteger('c', 5)));
    }

    @Test
    public void testGetLongestRunningSequence6() {
	CharAndInteger res = getLongestRunningSequence(new String[] { "aaaaa",
		"bbbb", "ccc", "bbbb" });
	assertThat(res, is(new CharAndInteger('b', 8)));
    }

    @Test
    public void testGetLongestRunningSequence7() {
	CharAndInteger res = getLongestRunningSequence(new String[] { "aaaaa",
		"bbbb", "ccc", "bbaa" });
	assertThat(res, is(new CharAndInteger('a', 7)));
    }

    @Test
    public void testGetLongestRunningSequence8() {
	CharAndInteger res = getLongestRunningSequence(new String[] { "aaaab",
		"bbbb", "ccc", "bbaa" });
	assertThat(res, is(new CharAndInteger('b', 7)));
    }

    @Test
    public void testGetLongestRunningSequence9() {
	CharAndInteger res = getLongestRunningSequence(new String[] { "aaaaa",
		"bbaab", "bbbb" });
	assertThat(res, is(new CharAndInteger('b', 6)));
    }

    @Test
    public void testGetLongestRunningSequence10() {
	CharAndInteger res = getLongestRunningSequence(new String[] { "aaaaa",
		"bbaaa", "bbbb" });
	assertThat(res, is(new CharAndInteger('a', 8)));
    }

    @Test
    public void testGetLongestRunningSequence11() {
	CharAndInteger res = getLongestRunningSequence(new String[] { "aaaaa",
		"ba", "ab", "bbbb" });
	assertThat(res, is(new CharAndInteger('a', 7)));
    }

    @Test
    public void testGetLongestRunningSequence12() {
	CharAndInteger res = getLongestRunningSequence(new String[] { "aaaaaa",
		"bbbbb", "abb", "caab" });
	assertThat(res, is(new CharAndInteger('a', 7)));
    }
}
