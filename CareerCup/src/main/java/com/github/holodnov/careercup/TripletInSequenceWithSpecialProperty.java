package com.github.holodnov.careercup;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=5683135085805568">http://www.careercup.com/question?id=5683135085805568</a>
 * 
 * @author Kyrylo Holodnov
 */
public class TripletInSequenceWithSpecialProperty {
    public static int[] findTriplet(int[] a) {
	if ((a == null) || (a.length < 3)) {
	    throw new IllegalArgumentException(
		    "Array should have at least 3 elements");
	}
	if ((a[0] < a[1]) || (a[a.length - 1] < a[a.length - 2])) {
	    throw new IllegalArgumentException(
		    "Array does not have special property");
	}
	if (a.length == 3) {
	    return new int[] { a[0], a[1], a[2] };
	}
	int left = 0;
	int right = a.length - 2;
	while (left < right) {
	    int middle = left + ((right - left) >> 1);
	    if (a[middle + 1] - a[middle] < 0) {
		left = middle + 1;
	    } else {
		right = middle;
	    }
	}
	return new int[] { a[left - 1], a[left], a[left + 1] };
    }
}
