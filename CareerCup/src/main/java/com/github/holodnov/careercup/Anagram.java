package com.github.holodnov.careercup;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=5136177443110912">http://www.careercup.com/question?id=5136177443110912</a>
 */
public class Anagram {

    public static boolean isAnagram(String first, String second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Input arguments cannot be null");
        }
        if (first.length() != second.length()) {
            return false;
        }
        int[] occurrences = new int[256];
        for (int i = 0; i < first.length(); i++) {
            int pos = first.charAt(i);
            if (pos < 0 || pos > 255) {
                throw new IllegalArgumentException("First string contains non-ASCII code at position #" + i);
            }
            occurrences[pos]++;
        }
        for (int i = 0; i < second.length(); i++) {
            int pos = second.charAt(i);
            if (pos < 0 || pos > 255) {
                throw new IllegalArgumentException("Second string contains non-ASCII code at position #" + i);
            }
            occurrences[pos]--;
            if (occurrences[pos] < 0) {
                return false;
            }
        }
        return true;
    }
}
