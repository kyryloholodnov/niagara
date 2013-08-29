package com.github.holodnov.careercup;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=5977321018228736">http://www.careercup.com/question?id=5977321018228736</a>
 * 
 * @author Kyrylo Holodnov
 */
public class JosephusProblem {
    public static int getLastPerson(int n, int k) {
	if ((n <= 0) || (k <= 0)) {
	    throw new IllegalArgumentException("Input arguments should be positive");
	}
	return getLastPersonSafe(n, k);
    }
    
    private static int getLastPersonSafe(int n, int k) {
	if (n == 1) {
	    return 0;
	}
	return (getLastPersonSafe(n - 1, k) + k) % n;
    }
}
