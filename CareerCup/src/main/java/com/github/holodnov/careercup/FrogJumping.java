package com.github.holodnov.careercup;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=24669663">http://www.careercup.com/question?id=24669663</a>
 */
public class FrogJumping {

    public static int findShortestWay(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Input array is null or empty");
        }
        int[] jumping = new int[array.length - 1];
        for (int i = jumping.length - 1; i >= 0; i--) {
            if (array[i] < 0) {
                throw new IllegalArgumentException("Element #" + i + " is negative: " + array[i]);
            }
            if (i + array[i] >= jumping.length) {
                jumping[i] = 1;
                continue;
            }
            jumping[i] = MAX_VALUE;
            if (array[i] == 0) {
                continue;
            }
            for (int j = 1; j <= array[i] && i + j < jumping.length; j++) {
                if (jumping[i + j] != MAX_VALUE) {
                    jumping[i] = min(1 + jumping[i + j], jumping[i]);
                }
            }
        }
        return jumping[0] == MAX_VALUE ? -1 : jumping[0];
    }
}
