package com.github.holodnov.careercup;

import java.util.Arrays;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=24890664">http://www.careercup.com/question?id=24890664</a>
 * 
 * @author Kyrylo Holodnov
 */
public class WeightedBinaryTree {
    public static int getMinimumTreeWeight(int[] array) {
	if ((array == null) || (array.length == 0)) {
	    throw new IllegalArgumentException("Input array is null or empty");
	}
	int level = 0;
	int weight = 0;
	Arrays.sort(array);
	for (int i = 0; i < array.length; i++) {
	    if (((i + 1) & i) == 0) {
		level++;
	    }
	    weight += level * array[array.length - 1 - i];
	}
	return weight;
    }
}
