package com.github.holodnov.careercup;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=22273665">http://www.careercup.com/question?id=22273665</a>
 * 
 * @author Kyrylo Holodnov
 */
public class StringDuplicates {
    public static int countDuplicatesWithHashSet(String sentence) {
	if (sentence == null) {
	    throw new IllegalArgumentException("Input sentence is null");
	}
	int res = 0;
	StringTokenizer tokens = new StringTokenizer(sentence);
	Set<String> unique = new HashSet<String>();
	while (tokens.hasMoreElements()) {
	    if (!unique.add(tokens.nextToken())) {
		res++;
	    }
	}
	return res;
    }

    public static int countDuplicatesWithTrie(String sentence) {
	if (sentence == null) {
	    throw new IllegalArgumentException("Input sentence is null");
	}
	int res = 0;
	StringTokenizer tokens = new StringTokenizer(sentence);
	Trie trie = new Trie();
	while (tokens.hasMoreElements()) {
	    if (!trie.insertWord(tokens.nextToken())) {
		res++;
	    }
	}
	return res;
    }
}
