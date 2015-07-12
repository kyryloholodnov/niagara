package com.github.holodnov.careercup;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=5181985580384256">http://www.careercup.com/question?id=5181985580384256</a>
 */
public class CharsReplacer {

    public static Set<String> generatePasswords(String word, Map<Character, char[]> replacer) {
        if (word == null) {
            throw new IllegalArgumentException("Input word should be not null");
        }
        if (replacer == null) {
            throw new IllegalArgumentException("Input replacer should be not null");
        }
        Set<String> passwords = new HashSet<>();
        passwords.add(word);
        generatePasswords(word, replacer, 0, passwords);
        return passwords;
    }

    private static void generatePasswords(String word,
                                          Map<Character, char[]> replacer,
                                          int pos,
                                          Set<String> passwords) {
        if (pos == word.length()) {
            return;
        }
        char[] replaceWith = replacer.get(word.charAt(pos));
        if (replaceWith != null) {
            for (char replaceChar : replaceWith) {
                Set<String> newlyGenerated = new HashSet<>();
                for (String password : passwords) {
                    if (pos == 0) {
                        newlyGenerated.add(replaceChar + password.substring(1));
                    } else {
                        newlyGenerated.add(password.substring(0, pos) + replaceChar + password.substring(pos + 1));
                    }
                }
                passwords.addAll(newlyGenerated);
            }
        }
        generatePasswords(word, replacer, pos + 1, passwords);
    }
}
