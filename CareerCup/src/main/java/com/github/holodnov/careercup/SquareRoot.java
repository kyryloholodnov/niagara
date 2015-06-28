package com.github.holodnov.careercup;

import java.math.BigDecimal;

import static java.math.BigDecimal.*;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=6657802751705088">http://www.careercup.com/question?id=6657802751705088</a>
 */
public class SquareRoot {

    private static final BigDecimal EPS = valueOf(0.0000001);
    private static final BigDecimal TWO = valueOf(2);
    private static final int SCALE = 14;

    public static BigDecimal getSquareRoot(BigDecimal number) {
        if (number == null) {
            throw new IllegalArgumentException("Input number is null");
        }
        if (number.signum() == -1) {
            throw new IllegalArgumentException("Input number is negative");
        }
        if (number.signum() == 0) {
            return ZERO;
        }
        BigDecimal root = ONE;
        BigDecimal prevRoot;
        do {
            prevRoot = root;
            root = root.add(number.divide(root, SCALE, ROUND_FLOOR)).divide(TWO, SCALE, ROUND_FLOOR);
        } while (root.subtract(prevRoot).abs().compareTo(EPS) >= 0);
        return root;
    }
}
