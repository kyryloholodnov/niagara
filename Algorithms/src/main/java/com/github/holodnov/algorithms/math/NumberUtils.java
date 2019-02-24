package com.github.holodnov.algorithms.math;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class NumberUtils {

    public static List<long[]> findFactoring(long b) {
        List<long[]> divs = new ArrayList<>();
        if (BigInteger.valueOf(b).isProbablePrime(100)) {
            divs.add(new long[]{b, 1});
            return divs;
        }
        for (long i = 2; i <= b; i++) {
            if (b % i == 0) {
                int deg = 0;
                while (b % i == 0) {
                    deg++;
                    b /= i;
                }
                divs.add(new long[]{i, deg});
                if (BigInteger.valueOf(b).isProbablePrime(100)) {
                    divs.add(new long[]{b, 1});
                    return divs;
                }
            }
        }
        return divs;
    }
}
