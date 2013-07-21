package com.github.holodnov.careercup;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
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
	boolean firstInsert = trie.insertWord("");
	assertThat(
		"First insertion of empty string to Trie should return true",
		firstInsert, is(true));
    }

    @Test
    public void testInsertWordEmptyTwice() {
	Trie trie = new Trie();
	boolean firstInsert = trie.insertWord("");
	assertThat(
		"First insertion of empty string to Trie should return true",
		firstInsert, is(true));
	boolean secondInsert = trie.insertWord("");
	assertThat(
		"Second insertion of empty string to empty Trie should return false",
		secondInsert, is(false));
    }

    @Test
    public void testInsertWordUniqueArray() {
	Trie trie = new Trie();
	String[] sequence = new String[] { "a", "", "stv", "aa", "bb", "b",
		"a9", "aA9" };
	for (int i = 0; i < sequence.length; i++) {
	    boolean insert = trie.insertWord(sequence[i]);
	    assertThat("Insertion should be ok", insert, is(true));
	}
    }

    @Test
    public void testContainsWordUniqueArray() {
	Trie trie = new Trie();
	String[] sequence = new String[] { "a", "", "stv", "aa", "bb", "b",
		"a9", "aA9" };
	for (int i = 0; i < sequence.length; i++) {
	    trie.insertWord(sequence[i]);
	}
	for (int i = 0; i < sequence.length; i++) {
	    boolean contains = trie.containsWord(sequence[i]);
	    assertThat("Contains should be true for: " + sequence[i], contains,
		    is(true));
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
