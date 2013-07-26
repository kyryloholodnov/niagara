package com.github.holodnov.careercup;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=23203670">http://www.careercup.com/question?id=23203670</a>
 * 
 * @author Kyrylo Holodnov
 */
public class TopKNumbersFromThreeSortedArrays {
    public static int[] findTopKNumbersFromThreeSortedArrays(int[] first,
	    int[] second, int[] third, int k) {
	if (k <= 0) {
	    throw new IllegalArgumentException("K should be positive number");
	}
	if ((first == null) || (second == null) || (third == null)) {
	    throw new IllegalArgumentException(
		    "All three arrays should be not null: "
			    + "first array is null - " + (first == null)
			    + "; second array is null - " + (second == null)
			    + "; third array is null - " + (third == null));
	}
	if (first.length + second.length + third.length < k) {
	    throw new IllegalArgumentException(
		    "The sum of arrays sizes should not be less than k");
	}
	int p1 = first.length - 1;
	int p2 = second.length - 1;
	int p3 = third.length - 1;
	int[] res = new int[k];
	while (k > 0) {
	    int max = Math.max(p1 >= 0 ? first[p1] : Integer.MIN_VALUE, Math
		    .max(p2 >= 0 ? second[p2] : Integer.MIN_VALUE,
			    p3 >= 0 ? third[p3] : Integer.MIN_VALUE));
	    res[k - 1] = max;
	    if ((p1 >= 0) && (max == first[p1])) {
		p1--;
	    } else if ((p2 >= 0) && (max == second[p2])) {
		p2--;
	    } else if ((p3 >= 0) && (max == third[p3])) {
		p3--;
	    }
	    k--;
	}
	return res;
    }
}
