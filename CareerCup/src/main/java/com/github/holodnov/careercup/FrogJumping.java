package com.github.holodnov.careercup;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=24669663">http://www.careercup.com/question?id=24669663</a>
 * 
 * @author Kyrylo Holodnov
 */
public class FrogJumping {
    public static int findShortestWay(int[] array) {
	if ((array == null) || (array.length == 0)) {
	    throw new IllegalArgumentException("Input array is null or empty");
	}
	int[] jumping = new int[array.length];
	for (int i = jumping.length - 1; i >= 0; i--) {
	    if (array[i] < 0) {
		throw new IllegalArgumentException("Element #" + i
			+ " is negative: " + array[i]);
	    }
	    if (array[i] == 0) {
		jumping[i] = Integer.MAX_VALUE / 2;
		continue;
	    }
	    if (i + array[i] >= jumping.length) {
		jumping[i] = 1;
		continue;
	    } else {
		jumping[i] = Integer.MAX_VALUE;
	    }
	    for (int j = 1; (j <= array[i]) && (i + j < jumping.length); j++) {
		jumping[i] = Math.min(1 + jumping[i + j], jumping[i]);
	    }
	}
	if (jumping[0] >= Integer.MAX_VALUE / 2) {
	    return Integer.MAX_VALUE;
	}
	return jumping[0];
    }
}
