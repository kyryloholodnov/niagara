package com.github.holodnov.careercup;

import java.util.Arrays;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=19778663">http://www.careercup.com/question?id=19778663</a>
 */
public class LongestConsecutiveNumbers {

    public static int[] findLongestConsecutiveNumbers(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            throw new IllegalArgumentException("Input sequence is null or empty");
        }
        Arrays.sort(sequence);
        int longest = 0;
        int i = 0;
        int start = -1;
        int end = -1;
        while (i < sequence.length) {
            int pos = i;
            while (pos < sequence.length && pos - i == sequence[pos] - sequence[i]) {
                pos++;
            }
            if (pos - i >= longest) {
                start = i;
                end = pos;
                longest = pos - i;
            }
            i = pos;
        }
        return Arrays.copyOfRange(sequence, start, end);
    }
}
