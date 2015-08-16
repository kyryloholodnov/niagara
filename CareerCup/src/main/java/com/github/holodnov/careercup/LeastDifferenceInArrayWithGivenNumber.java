package com.github.holodnov.careercup;

import static java.lang.Math.abs;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=20982670">http://www.careercup.com/question?id=20982670</a>
 */
public class LeastDifferenceInArrayWithGivenNumber {

    public static int getElementWithLeastDifference(int[] sequence, int element) {
        if (sequence == null || sequence.length == 0) {
            throw new IllegalArgumentException("Input array is null or empty");
        }
        int left = 0;
        int right = sequence.length - 1;
        while (left < right) {
            int median = left + ((right - left) >> 1);
            if (sequence[median] < element) {
                left = median + 1;
            } else {
                right = median;
            }
        }
        if (sequence[left] == element) {
            return element;
        } else {
            if (left == 0) {
                return sequence[0];
            } else {
                if (abs(element - sequence[left]) >= abs(element - sequence[left - 1])) {
                    return sequence[left - 1];
                } else {
                    return sequence[left];
                }
            }
        }
    }
}
