package com.github.holodnov.careercup;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=23806685">http://www.careercup.com/question?id=23806685</a>
 */
public class StringMatchesPatternString {

    public static boolean matches(String str, String pattern) {
        if (str == null || pattern == null) {
            throw new IllegalArgumentException("Tested string either pattern string is null");
        }
        Boolean[][] cache = new Boolean[str.length() + 1][pattern.length() + 1];
        return matches(str, pattern, 0, 0, cache);
    }

    private static Boolean matches(String str, String pattern, int strPos, int patternPos, Boolean[][] cache) {
        if (cache[strPos][patternPos] != null) {
            return cache[strPos][patternPos];
        }
        if (patternPos == pattern.length()) {
            return cache[strPos][patternPos] = strPos == str.length();
        }
        if (pattern.charAt(patternPos) == '*') {
            for (int i = strPos; i <= str.length(); i++) {
                if (matches(str, pattern, i, patternPos + 1, cache)) {
                    return cache[strPos][patternPos] = TRUE;
                }
            }
            return cache[strPos][patternPos] = FALSE;
        } else if (pattern.charAt(patternPos) == '?') {
            if (strPos == str.length()) {
                return cache[strPos][patternPos] = FALSE;
            }
            return cache[strPos][patternPos] = matches(str, pattern, strPos + 1, patternPos + 1, cache);
        } else {
            if (strPos == str.length() || str.charAt(strPos) != pattern.charAt(patternPos)) {
                return cache[strPos][patternPos] = FALSE;
            }
            return cache[strPos][patternPos] = matches(str, pattern, strPos + 1, patternPos + 1, cache);
        }
    }
}
