package com.github.holodnov.careercup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=5711103912837120">http://www.careercup.com/question?id=5711103912837120</a>
 * 
 * @author Kyrylo Holodnov
 */
public class DictionaryWordsInTiles {

    public static String[] findDictionaryWords(String tiles, String[] dictionary) {
	if ((tiles == null) || (tiles.length() == 0) || (dictionary == null)
		|| (dictionary.length == 0)) {
	    return new String[0];
	}
	Map<Character, Integer> tilesMap = new HashMap<Character, Integer>();
	for (int i = 0; i < tiles.length(); i++) {
	    Integer cnt = tilesMap.get(tiles.charAt(i));
	    if (cnt == null) {
		cnt = Integer.valueOf(0);
	    }
	    tilesMap.put(tiles.charAt(i), cnt + 1);
	}
	List<String> result = new ArrayList<String>();
	for (int i = 0; i < dictionary.length; i++) {
	    boolean accepted = true;
	    Map<Character, Integer> words = new HashMap<Character, Integer>();
	    for (int j = 0; j < dictionary[i].length(); j++) {
		Integer cnt = tilesMap.get(dictionary[i].charAt(j));
		if (cnt == null) {
		    accepted = false;
		    break;
		}
		Integer cntInDictionary = words.get(dictionary[i].charAt(j));
		if (cntInDictionary == null) {
		    cntInDictionary = Integer.valueOf(0);
		}
		cntInDictionary++;
		if (cntInDictionary > cnt) {
		    accepted = false;
		    break;
		}
		words.put(dictionary[i].charAt(j), cntInDictionary);
	    }
	    if (accepted) {
		result.add(dictionary[i]);
	    }
	}
	return result.toArray(new String[0]);
    }
}
