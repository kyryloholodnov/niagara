package com.github.holodnov.careercup;

import java.util.Arrays;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=19778663">http://www.careercup.com/question?id=19778663</a>
 * 
 * @author Kyrylo Holodnov
 */
public class LongestConsecutiveNumbers {
    public static int[] findLongestConsecutiveNumbers(int[] sequence) {
	if ((sequence == null) || (sequence.length == 0)) {
	    throw new IllegalArgumentException("Sequence is null or empty");
	}
	Arrays.sort(sequence);
	int res = 0;
	int i = 0;
	int start = -1;
	int end = -1;
	while (i < sequence.length) {
	    int pos = i;
	    while ((pos < sequence.length)
		    && (pos - i == sequence[pos] - sequence[i])) {
		pos++;
	    }
	    if (pos - i >= res) {
		start = i;
		end = pos;
		res = pos - i;
	    }
	    i = pos;
	}
	int[] array = new int[end - start];
	System.arraycopy(sequence, start, array, 0, end - start);
	return array;
    }
}
