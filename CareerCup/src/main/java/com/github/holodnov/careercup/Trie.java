package com.github.holodnov.careercup;

/**
 * 
 * @author Kyrylo Holodnov
 */
public class Trie {

    private TrieNode parent;
    private int size;

    public Trie() {
	parent = new TrieNode('\0');
	size = 0;
    }

    /**
     * Inserts new word in Trie. Returns true if inserted, false if Trie already
     * contains this word.
     * 
     * @param s
     *            input word
     * @throws IllegalArgumentException
     *             if input word is null or contains character not in
     *             [a-zA-Z0-9]
     */
    public boolean insertWord(String s) {
	if (s == null) {
	    throw new IllegalArgumentException("Input string is null");
	}
	TrieNode node = parent;
	for (int i = 0; i < s.length(); i++) {
	    node = node.getChild(s.charAt(i));
	}
	if (node.endOfWord) {
	    return false;
	}
	node.endOfWord = true;
	size++;
	return true;
    }

    /**
     * Tests if current Trie already contains given word.
     * 
     * @param s
     *            input word
     * @throws IllegalArgumentException
     *             if input word is null or contains character not in
     *             [a-zA-Z0-9]
     */
    public boolean containsWord(String s) {
	if (s == null) {
	    throw new IllegalArgumentException("Input string is null");
	}
	if (size == 0) {
	    return false;
	}
	TrieNode node = parent;
	for (int i = 0; i < s.length(); i++) {
	    node = node.getChildOrNull(s.charAt(i));
	    if (node == null) {
		return false;
	    }
	}
	return node.endOfWord;
    }

    /**
     * Tests if current Trie already contains word such that input word is
     * prefix of word in Trie.
     * 
     * @param s
     *            input word
     * @throws IllegalArgumentException
     *             if input word is null or contains character not in
     *             [a-zA-Z0-9]
     */
    public boolean containsAsPrefix(String s) {
	if (s == null) {
	    throw new IllegalArgumentException("Input string is null");
	}
	if (size == 0) {
	    return false;
	}
	TrieNode node = parent;
	for (int i = 0; i < s.length(); i++) {
	    node = node.getChildOrNull(s.charAt(i));
	    if (node == null) {
		return false;
	    }
	}
	return true;
    }

    /**
     * Tests if current Trie already contains word such that this word is prefix
     * of input word.
     * 
     * @param s
     *            input word
     * @throws IllegalArgumentException
     *             if input word is null or contains character not in
     *             [a-zA-Z0-9]
     */
    public boolean containsPrefixOf(String s) {
	if (s == null) {
	    throw new IllegalArgumentException("Input string is null");
	}
	if (size == 0) {
	    return false;
	}
	if (containsWord("")) {
	    return true;
	}
	TrieNode node = parent;
	for (int i = 0; i < s.length(); i++) {
	    node = node.getChildOrNull(s.charAt(i));
	    if (node == null) {
		return false;
	    }
	    if (node.endOfWord) {
		return true;
	    }
	}
	return false;
    }

    public int getSize() {
	return size;
    }

    private static class TrieNode {
	char c;
	boolean endOfWord;
	TrieNode[] children;

	public TrieNode(char c) {
	    this.c = c;
	    this.endOfWord = false;

	}

	private void lazyInitChildren() {
	    if (children == null) {
		children = new TrieNode[62];
	    }
	}

	public TrieNode getChild(char c) {
	    int pos = decode(c);
	    if (pos == -1) {
		throw new IllegalArgumentException("Character '" + c
			+ "' cannot be in string");
	    }
	    lazyInitChildren();
	    if (children[pos] == null) {
		children[pos] = new TrieNode(c);
	    }
	    return children[pos];
	}

	public TrieNode getChildOrNull(char c) {
	    int pos = decode(c);
	    if (pos == -1) {
		throw new IllegalArgumentException("Character '" + c
			+ "' cannot be in word: only [a-zA-Z0-9] available");
	    }
	    lazyInitChildren();
	    return children[pos];
	}

	private int decode(char c) {
	    int diff = c - 'a';
	    if ((diff >= 0) && (diff < 26)) {
		return diff;
	    }
	    diff = c - 'A';
	    if ((diff >= 0) && (diff < 26)) {
		return 26 + diff;
	    }
	    diff = c - '0';
	    if ((diff >= 0) && (diff < 10)) {
		return 52 + diff;
	    }
	    return -1;
	}
    }
}
