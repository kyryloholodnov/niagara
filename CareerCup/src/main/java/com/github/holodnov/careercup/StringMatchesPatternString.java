package com.github.holodnov.careercup;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=23806685">http://www.careercup.com/question?id=23806685</a>
 * 
 * @author Kyrylo Holodnov
 */
public class StringMatchesPatternString {
    public static boolean matches(String str, String pattern) {
	if ((str == null) || (pattern == null)) {
	    throw new IllegalArgumentException(
		    "Tested string either pattern string is null");
	}
	return matches(str, 0, pattern, 0);
    }

    private static boolean matches(String str, int posAtStr, String pattern,
	    int posAtPattern) {
	int j = posAtStr;
	for (int i = posAtPattern; i < pattern.length(); i++) {
	    if (pattern.charAt(i) == '?') {
		if (j == str.length()) {
		    return false;
		}
		j++;
	    } else if (pattern.charAt(i) == '*') {
		if (i == pattern.length() - 1) {
		    return true;
		}
		for (int k = j; k < str.length() + 1; k++) {
		    if (matches(str, k, pattern, i + 1)) {
			return true;
		    }
		}
		return false;
	    } else {
		if ((j == str.length()) || (str.charAt(j) != pattern.charAt(i))) {
		    return false;
		}
		j++;
	    }
	}
	return (j == str.length());
    }
}
