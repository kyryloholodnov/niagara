package com.github.holodnov.careercup;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Kyrylo Holodnov
 */
public class TrieTest {

    @Test(expected = IllegalArgumentException.class)
    public void testInsertWordWithNull() {
        Trie trie = new Trie();
        trie.insertWord(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertWordIncorrectChar1() {
        Trie trie = new Trie();
        trie.insertWord("cash$");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertWordIncorrectChar2() {
        Trie trie = new Trie();
        trie.insertWord("Two words");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertWordIncorrectChar3() {
        Trie trie = new Trie();
        trie.insertWord("Two,words");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertWordIncorrectChar4() {
        Trie trie = new Trie();
        trie.insertWord("I am a sentence");
    }

    @Test
    public void testInsertWordWithEmpty() {
        Trie trie = new Trie();
        assertTrue("First insertion of empty string to Trie should return true",
                trie.insertWord(""));
    }

    @Test
    public void testInsertWordEmptyTwice() {
        Trie trie = new Trie();
        assertTrue("First insertion of empty string to Trie should return true",
                trie.insertWord(""));
        assertFalse("Second insertion of empty string to empty Trie should return false",
                trie.insertWord(""));
    }

    @Test
    public void testInsertWordUniqueArray() {
        Trie trie = new Trie();
        String[] sequence = new String[]{"a", "", "stv", "aa", "bb", "b", "a9", "aA9"};
        for (String element : sequence) {
            assertTrue("Insertion should be ok", trie.insertWord(element));
        }
    }

    @Test
    public void testContainsWordUniqueArray() {
        Trie trie = new Trie();
        String[] sequence = new String[]{"a", "", "stv", "aa", "bb", "b", "a9", "aA9"};
        for (String element : sequence) {
            trie.insertWord(element);
        }
        for (String element : sequence) {
            assertTrue("Contains should be true for: " + element, trie.containsWord(element));
        }
    }

    @Test
    public void testInsertWord1() {
        Trie trie = new Trie();
        assertFalse(trie.containsWord("azAZ09"));
        assertTrue(trie.insertWord("azAZ09"));
        assertTrue(trie.containsWord("azAZ09"));
    }

    @Test
    public void testInsertWord2() {
        Trie trie = new Trie();
        assertFalse(trie.containsWord(""));
        assertTrue(trie.insertWord(""));
        assertTrue(trie.containsWord(""));
        assertFalse(trie.insertWord(""));
        assertFalse(trie.containsWord("abcdef"));
        assertTrue(trie.insertWord("abcdef"));
        assertTrue(trie.containsWord("abcdef"));
        assertFalse(trie.insertWord("abcdef"));
        assertFalse(trie.containsWord("a"));
        assertTrue(trie.insertWord("a"));
        assertTrue(trie.containsWord("a"));
        assertFalse(trie.insertWord("a"));
        assertFalse(trie.containsWord("abcde"));
        assertTrue(trie.insertWord("abcde"));
        assertTrue(trie.containsWord("abcde"));
        assertFalse(trie.insertWord("abcde"));
        assertFalse(trie.containsWord("abcde9"));
        assertTrue(trie.insertWord("abcde9"));
        assertTrue(trie.containsWord("abcde9"));
        assertFalse(trie.insertWord("abcde9"));
        assertFalse(trie.containsWord("abxXzZoOe9"));
        assertTrue(trie.insertWord("abxXzZoOe9"));
        assertTrue(trie.containsWord("abxXzZoOe9"));
        assertFalse(trie.insertWord("abxXzZoOe9"));
    }

    @Test
    public void testContainsAsPrefix1() {
        Trie trie = new Trie();
        assertFalse(trie.containsAsPrefix(""));
        trie.insertWord("a");
        assertTrue(trie.containsAsPrefix(""));
    }

    @Test
    public void testContainsAsPrefix2() {
        Trie trie = new Trie();
        trie.insertWord("");
        trie.insertWord("abc");
        trie.insertWord("abcde");
        assertTrue(trie.containsAsPrefix(""));
        assertTrue(trie.containsAsPrefix("ab"));
        assertTrue(trie.containsAsPrefix("abc"));
        assertTrue(trie.containsAsPrefix("abcd"));
        assertTrue(trie.containsAsPrefix("abcde"));
        assertFalse(trie.containsAsPrefix("abcdef"));
        assertFalse(trie.containsAsPrefix("bcde"));
    }

    @Test
    public void testContaintsPrefixOf1() {
        Trie trie = new Trie();
        assertFalse(trie.containsPrefixOf(""));
        assertFalse(trie.containsPrefixOf("a"));
        assertFalse(trie.containsPrefixOf("abc"));
        trie.insertWord("abc");
        trie.insertWord("abcde");
        assertFalse(trie.containsPrefixOf(""));
        assertFalse(trie.containsPrefixOf("a"));
        assertFalse(trie.containsPrefixOf("b"));
        assertFalse(trie.containsPrefixOf("ab"));
        assertTrue(trie.containsPrefixOf("abc"));
        assertTrue(trie.containsPrefixOf("abcd"));
        assertTrue(trie.containsPrefixOf("abce"));
        assertTrue(trie.containsPrefixOf("abcf"));
        assertFalse(trie.containsPrefixOf("abd"));
        trie.insertWord("");
        assertTrue(trie.containsPrefixOf("abd"));
    }

    @Test
    public void testContaintsPrefixOf2() {
        Trie trie = new Trie();
        assertFalse(trie.containsPrefixOf("abcdefgh"));
        trie.insertWord("abcdefgh");
        assertFalse(trie.containsPrefixOf("a"));
        assertFalse(trie.containsPrefixOf("ab"));
        assertFalse(trie.containsPrefixOf("abc"));
        assertFalse(trie.containsPrefixOf("abcd"));
        assertFalse(trie.containsPrefixOf("abcde"));
        assertFalse(trie.containsPrefixOf("abcdef"));
        assertFalse(trie.containsPrefixOf("abcdefg"));
        assertTrue(trie.containsPrefixOf("abcdefgh"));
        assertFalse(trie.containsPrefixOf("abcdefgk"));
    }

    @Test
    public void testGetSizeEmptyTrie() {
        Trie trie = new Trie();
        assertThat(trie.getSize(), is(0));
    }

    @Test
    public void testGetSize1() {
        Trie trie = new Trie();
        trie.insertWord("azAZ09");
        assertThat(trie.getSize(), is(1));
    }

    @Test
    public void testGetSize2() {
        Trie trie = new Trie();
        trie.insertWord("");
        trie.insertWord("azAZ09");
        assertThat(trie.getSize(), is(2));
    }

    @Test
    public void testGetSize3() {
        Trie trie = new Trie();
        trie.insertWord("");
        trie.insertWord("azAZ09");
        trie.insertWord("");
        assertThat(trie.getSize(), is(2));
    }

    @Test
    public void testGetSize4() {
        Trie trie = new Trie();
        trie.insertWord("");
        trie.insertWord("azAZ09");
        trie.insertWord("");
        trie.insertWord("azAZ09");
        assertThat(trie.getSize(), is(2));
    }
}
