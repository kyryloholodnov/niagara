package com.github.holodnov.careercup;

import java.util.HashMap;
import java.util.Map;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=22360665">http://www.careercup.com/question?id=22360665</a>
 * 
 * @author Kyrylo Holodnov
 */
public class RemoveDuplicatesFromArray {
    public static int[] removeDuplicates(int[] sequence) {
	if ((sequence == null) || (sequence.length == 0)) {
	    return sequence;
	}
	Map<Integer, Integer> unique = new HashMap<Integer, Integer>();
	int cnt = 0;
	for (int i = 0; i < sequence.length; i++) {
	    Integer pos = unique.get(sequence[i]);
	    if (pos == null) {
		unique.put(sequence[i], cnt);
		cnt++;
	    }
	}
	if (sequence.length == cnt) {
	    return sequence;
	}
	int[] res = new int[cnt];
	for (Map.Entry<Integer, Integer> entry : unique.entrySet()) {
	    res[entry.getValue()] = entry.getKey();
	}
	return res;
    }
}
