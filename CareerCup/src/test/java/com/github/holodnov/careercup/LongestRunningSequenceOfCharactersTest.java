package com.github.holodnov.careercup;

import org.junit.Test;

import java.util.AbstractMap.SimpleImmutableEntry;

import static com.github.holodnov.careercup.LongestRunningSequenceOfCharacters.getLongestRunningSequence;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=20235671">http://www.careercup.com/question?id=20235671</a>
 */
public class LongestRunningSequenceOfCharactersTest {

    @Test
    public void testGetLongestRunningSequenceNullArray() {
        assertNull(getLongestRunningSequence(null));
    }

    @Test
    public void testGetLongestRunningSequenceEmptyArray() {
        assertNull(getLongestRunningSequence(new String[0]));
    }

    @Test
    public void testGetLongestRunningSequenceArrayWithEmpties() {
        assertNull(getLongestRunningSequence(new String[]{"", null, null, "", ""}));
    }

    @Test
    public void testGetLongestRunningSequence1() {
        assertThat(getLongestRunningSequence(new String[]{"a", "ab", "bba"}), is(new SimpleImmutableEntry<>('b', 3)));
    }

    @Test
    public void testGetLongestRunningSequence2() {
        assertThat(getLongestRunningSequence(new String[]{"aaa", "ab", "bba"}), is(new SimpleImmutableEntry<>('a', 5)));
    }

    @Test
    public void testGetLongestRunningSequence3() {
        assertThat(getLongestRunningSequence(new String[]{"ab", "ba", "", null, "aac"}), is(new SimpleImmutableEntry<>('a', 3)));
    }

    @Test
    public void testGetLongestRunningSequence4() {
        assertThat(getLongestRunningSequence(new String[]{"aaaa", "aaa", "aa", "a"}), is(new SimpleImmutableEntry<>('a', 10)));
    }

    @Test
    public void testGetLongestRunningSequence5() {
        assertThat(getLongestRunningSequence(new String[]{"aaaa", "bbb", "ccccc", "d"}), is(new SimpleImmutableEntry<>('c', 5)));
    }

    @Test
    public void testGetLongestRunningSequence6() {
        assertThat(getLongestRunningSequence(new String[]{"aaaaa", "bbbb", "ccc", "bbbb"}), is(new SimpleImmutableEntry<>('b', 8)));
    }

    @Test
    public void testGetLongestRunningSequence7() {
        assertThat(getLongestRunningSequence(new String[]{"aaaaa", "bbbb", "ccc", "bbaa"}), is(new SimpleImmutableEntry<>('a', 7)));
    }

    @Test
    public void testGetLongestRunningSequence8() {
        assertThat(getLongestRunningSequence(new String[]{"aaaab", "bbbb", "ccc", "bbaa"}), is(new SimpleImmutableEntry<>('b', 7)));
    }

    @Test
    public void testGetLongestRunningSequence9() {
        assertThat(getLongestRunningSequence(new String[]{"aaaaa", "bbaab", "bbbb"}), is(new SimpleImmutableEntry<>('b', 6)));
    }

    @Test
    public void testGetLongestRunningSequence10() {
        assertThat(getLongestRunningSequence(new String[]{"aaaaa", "bbaaa", "bbbb"}), is(new SimpleImmutableEntry<>('a', 8)));
    }

    @Test
    public void testGetLongestRunningSequence11() {
        assertThat(getLongestRunningSequence(new String[]{"aaaaa", "ba", "ab", "bbbb"}), is(new SimpleImmutableEntry<>('a', 7)));
    }

    @Test
    public void testGetLongestRunningSequence12() {
        assertThat(getLongestRunningSequence(new String[]{"aaaaaa", "bbbbb", "abb", "caab"}), is(new SimpleImmutableEntry<>('a', 7)));
    }
}
