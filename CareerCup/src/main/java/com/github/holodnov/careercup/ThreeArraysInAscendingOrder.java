package com.github.holodnov.careercup;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=6581732438441984">http://www.careercup.com/question?id=6581732438441984</a>
 * 
 * @author Kyrylo Holodnov
 */
public class ThreeArraysInAscendingOrder {
    public static int getMinimumSum(int[] a, int[] b, int[] c) {
	if ((a == null) || (a.length == 0)) {
	    throw new IllegalArgumentException("First array is null or empty");
	}
	if ((b == null) || (b.length == 0)) {
	    throw new IllegalArgumentException("Second array is null or empty");
	}
	if ((c == null) || (c.length == 0)) {
	    throw new IllegalArgumentException("Third array is null or empty");
	}
	int sum = Integer.MAX_VALUE;
	int i = 0;
	int j = 0;
	int k = 0;
	while ((i < a.length) && (j < b.length) && (k < c.length)) {
	    int s = Math.abs(a[i] - b[j]) + Math.abs(b[j] - c[k])
		    + Math.abs(c[k] - a[i]);
	    if (s < sum) {
		sum = s;
	    }
	    if ((a[i] <= b[j]) && (a[i] <= c[k])) {
		i++;
	    } else if ((b[j] <= a[i]) && (b[j] <= c[k])) {
		j++;
	    } else if ((c[k] <= a[i]) && (c[k] <= b[j])) {
		k++;
	    }
	}
	return sum;
    }
}
