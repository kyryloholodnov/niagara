package com.github.holodnov.careercup;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=22049663">http://www.careercup.com/question?id=22049663</a>
 */
public class BinaryTreesCount {

    public static BigInteger findBinaryTreesCount(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input number of vertices is not positive");
        }
        return findBinaryTreesCount(n, new BigInteger[n + 1]);
    }

    private static BigInteger findBinaryTreesCount(int n, BigInteger[] cache) {
        if (cache[n] != null) {
            return cache[n];
        }
        if (n == 0 || n == 1) {
            return cache[n] = ONE;
        }
        BigInteger treesCount = ZERO;
        for (int i = 0; i < n; i++) {
            BigInteger s = findBinaryTreesCount(n - 1 - i, cache).multiply(findBinaryTreesCount(i, cache));
            treesCount = treesCount.add(s);
        }
        return cache[n] = treesCount;
    }
}
