package com.github.holodnov.careercup;

import java.math.BigInteger;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=22049663">http://www.careercup.com/question?id=22049663</a>
 * 
 * @author Kyrylo Holodnov
 */
public class BinaryTreesCount {
    public static BigInteger findBinaryTreesCount(int n) {
	if (n <= 0) {
	    throw new IllegalArgumentException(
		    "Input number of vertices is not positive");
	}
	BigInteger[] cache = new BigInteger[n + 1];
	return findBinaryTreesCount(n, cache);
    }

    private static BigInteger findBinaryTreesCount(int n, BigInteger[] cache) {
	if (cache[n] != null) {
	    return cache[n];
	}
	if ((n == 0) || (n == 1)) {
	    cache[n] = BigInteger.ONE;
	    return cache[n];
	}
	BigInteger res = BigInteger.ZERO;
	for (int i = 0; i < n; i++) {
	    BigInteger s = findBinaryTreesCount(n - 1 - i, cache).multiply(
		    findBinaryTreesCount(i, cache));
	    res = res.add(s);
	}
	cache[n] = res;
	return res;
    }
}
