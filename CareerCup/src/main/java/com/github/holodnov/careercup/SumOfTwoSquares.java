package com.github.holodnov.careercup;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=5767787551129600">http://www.careercup.com/question?id=5767787551129600</a>
 * 
 * @author Kyrylo Holodnov
 */
public class SumOfTwoSquares {

    public static int findNumberOfWays(int value) {
	if (value < 0) {
	    return 0;
	}
	if (value <= 1) {
	    return 1;
	}
	int left = 0;
	int right = (int) Math.sqrt(value);
	int numberOfWays = 0;
	while (left <= right) {
	    long sum = (left + 0L) * left + (right + 0L) * right;
	    if (sum < value) {
		left++;
	    } else if (sum > value) {
		right--;
	    } else {
		numberOfWays++;
		left++;
		right--;
	    }
	}
	return numberOfWays;
    }
}
