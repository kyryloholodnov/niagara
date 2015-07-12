package com.github.holodnov.careercup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=5711103912837120">http://www.careercup.com/question?id=5711103912837120</a>
 */
public class DictionaryWordsInTiles {

    public static String[] findDictionaryWords(String tiles, String[] dictionary) {
        if (tiles == null || tiles.isEmpty() || dictionary == null || dictionary.length == 0) {
            return new String[0];
        }
        Map<Character, Integer> tilesMap = new HashMap<>();
        for (int i = 0; i < tiles.length(); i++) {
            Integer cnt = tilesMap.get(tiles.charAt(i));
            if (cnt == null) {
                cnt = 0;
            }
            tilesMap.put(tiles.charAt(i), cnt + 1);
        }
        List<String> correctWords = new ArrayList<>();
        for (String word : dictionary) {
            boolean accepted = true;
            Map<Character, Integer> words = new HashMap<>();
            for (int j = 0; j < word.length(); j++) {
                Integer cnt = tilesMap.get(word.charAt(j));
                if (cnt == null) {
                    accepted = false;
                    break;
                }
                Integer cntInDictionary = words.get(word.charAt(j));
                if (cntInDictionary == null) {
                    cntInDictionary = 0;
                }
                cntInDictionary++;
                if (cntInDictionary > cnt) {
                    accepted = false;
                    break;
                }
                words.put(word.charAt(j), cntInDictionary);
            }
            if (accepted) {
                correctWords.add(word);
            }
        }
        return correctWords.toArray(new String[correctWords.size()]);
    }
}
