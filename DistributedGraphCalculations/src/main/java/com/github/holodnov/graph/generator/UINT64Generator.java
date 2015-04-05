package com.github.holodnov.graph.generator;

import java.math.BigInteger;
import java.security.SecureRandom;

import static java.math.BigInteger.ZERO;

/**
 * @author Kyrylo Holodnov
 */
public class UINT64Generator {

    private static final SecureRandom random = new SecureRandom();
    private static final BigInteger TWO_POWER_63_MINUS_ONE = new BigInteger("9223372036854775807");

    public String generate() {
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);
        BigInteger value = new BigInteger(bytes);
        return value.compareTo(ZERO) < 0 ? TWO_POWER_63_MINUS_ONE.subtract(value).toString() : value.toString();
    }
}
