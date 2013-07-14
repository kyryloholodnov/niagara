package com.github.holodnov.careercup;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=5200260502650880">http://www.careercup.com/question?id=5200260502650880</a>
 * 
 * @author Kyrylo Holodnov
 */
public class MajorityVoteAlgorithm {
    public static int findMajorityElement(int[] sequence) {
	if ((sequence == null) || (sequence.length == 0)) {
	    throw new IllegalArgumentException("Input array is null or empty");
	}
	int cnt = 0;
	int currentElement = 0;
	for (int i = 0; i < sequence.length; i++) {
	    if (cnt == 0) {
		currentElement = sequence[i];
		cnt = 1;
	    } else if (sequence[i] == currentElement) {
		cnt++;
	    } else {
		cnt--;
	    }
	}
	int occurence = 0;
	for (int i = 0; i < sequence.length; i++) {
	    if (sequence[i] == currentElement) {
		occurence++;
	    }
	}
	if ((occurence << 1) >= sequence.length) {
	    return currentElement;
	} else {
	    throw new IllegalArgumentException(
		    "Input array does not have majority element");
	}
    }
}
