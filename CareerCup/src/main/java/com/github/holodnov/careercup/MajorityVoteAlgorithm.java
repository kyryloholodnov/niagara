package com.github.holodnov.careercup;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=5200260502650880">http://www.careercup.com/question?id=5200260502650880</a>
 */
public class MajorityVoteAlgorithm {

    public static int findMajorityElement(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            throw new IllegalArgumentException("Input array is null or empty");
        }
        int cnt = 0;
        int currentElement = 0;
        for (int element : sequence) {
            if (cnt == 0) {
                currentElement = element;
                cnt = 1;
            } else if (element == currentElement) {
                cnt++;
            } else {
                cnt--;
            }
        }
        int occurrence = 0;
        for (int element : sequence) {
            if (element == currentElement) {
                occurrence++;
            }
        }
        if (occurrence << 1 >= sequence.length) {
            return currentElement;
        } else {
            throw new IllegalArgumentException("Input array does not have majority element");
        }
    }
}
