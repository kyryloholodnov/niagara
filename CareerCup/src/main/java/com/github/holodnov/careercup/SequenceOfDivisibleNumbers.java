package com.github.holodnov.careercup;

import java.math.BigInteger;
import java.util.TreeSet;

import static java.math.BigInteger.ONE;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=20810664">http://www.careercup.com/question?id=20810664</a>
 */
public class SequenceOfDivisibleNumbers {

    private static BigInteger TWO = BigInteger.valueOf(2);
    private static BigInteger THREE = BigInteger.valueOf(3);
    private static BigInteger FIVE = BigInteger.valueOf(5);

    public static BigInteger getNthNumber(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input parameter should be positive");
        }
        TreeSet<BigInteger> set = new TreeSet<>();
        set.add(ONE);
        BigInteger nthNumber = ONE;
        while (n > 0) {
            nthNumber = set.ceiling(nthNumber);
            set.remove(nthNumber);
            set.add(nthNumber.multiply(TWO));
            set.add(nthNumber.multiply(THREE));
            set.add(nthNumber.multiply(FIVE));
            n--;
        }
        return nthNumber;
    }
}
