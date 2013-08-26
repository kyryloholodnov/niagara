package com.github.holodnov.careercup;

import java.math.BigDecimal;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=6657802751705088">http://www.careercup.com/question?id=6657802751705088</a>
 * 
 * @author Kyrylo Holodnov
 */
public class SquareRoot {
    private static final BigDecimal EPS = BigDecimal.valueOf(0.0000001);
    private static final BigDecimal TWO = BigDecimal.valueOf(2);
    private static final int SCALE = 14;
    private static final int ROUNDING_MODE = BigDecimal.ROUND_FLOOR;

    public static BigDecimal getSquareRoot(BigDecimal number) {
	if (number == null) {
	    throw new IllegalArgumentException("Input number is null");
	}
	if (number.signum() == -1) {
	    throw new IllegalArgumentException("Input number is negative");
	}
	if (number.signum() == 0) {
	    return BigDecimal.ZERO;
	}
	BigDecimal root = BigDecimal.ONE;
	BigDecimal prevRoot = null;
	do {
	    prevRoot = root;
	    root = root.add(number.divide(root, SCALE, ROUNDING_MODE)).divide(TWO, SCALE, ROUNDING_MODE);
	} while (root.subtract(prevRoot).abs().compareTo(EPS) >= 0);
	return root;
    }

}
