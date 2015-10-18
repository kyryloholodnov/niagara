package com.github.holodnov.careercup;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=22360665">http://www.careercup.com/question?id=22360665</a>
 */
public class RemoveDuplicatesFromArray {

    public static int[] removeDuplicates(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return sequence;
        }
        Map<Integer, Integer> unique = new HashMap<>();
        int cnt = 0;
        for (int element : sequence) {
            Integer pos = unique.get(element);
            if (pos == null) {
                unique.put(element, cnt);
                cnt++;
            }
        }
        if (sequence.length == cnt) {
            return sequence;
        }
        int[] result = new int[cnt];
        for (Entry<Integer, Integer> entry : unique.entrySet()) {
            result[entry.getValue()] = entry.getKey();
        }
        return result;
    }
}
