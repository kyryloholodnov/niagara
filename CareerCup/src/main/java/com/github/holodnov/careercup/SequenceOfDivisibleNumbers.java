package com.github.holodnov.careercup;

import java.util.TreeSet;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=20810664">http://www.careercup.com/question?id=20810664</a>
 */
public class SequenceOfDivisibleNumbers {

    public static long getNthNumber(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input parameter should be positive");
        }
        TreeSet<Long> set = new TreeSet<>();
        set.add(1L);
        long nthNumber = 1;
        while (n > 0) {
            nthNumber = set.ceiling(nthNumber);
            set.remove(nthNumber);
            set.add(2 * nthNumber);
            set.add(3 * nthNumber);
            set.add(5 * nthNumber);
            n--;
        }
        return nthNumber;
    }
}
