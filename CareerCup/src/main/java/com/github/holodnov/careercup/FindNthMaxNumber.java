package com.github.holodnov.careercup;

import java.util.PriorityQueue;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=20967663">http://www.careercup.com/question?id=20967663</a>
 */
public class FindNthMaxNumber {

    public static int findNthMaxNumberUsingSelectionSearch(int[] sequence, int n) {
        if (sequence == null) {
            throw new IllegalArgumentException("Input array is null");
        }
        if (n < 1) {
            throw new IllegalArgumentException("Input n is non-positive");
        }
        if (sequence.length < n) {
            throw new IllegalArgumentException("Array size is less than input n");
        }
        return findNthMaxNumberUsingSelectionSearch(sequence, n - 1, 0, sequence.length - 1);
    }

    private static int findNthMaxNumberUsingSelectionSearch(int[] sequence, int n, int left, int right) {
        if (left == right) {
            return sequence[left];
        }
        if (n == left) {
            int max = sequence[left];
            for (int i = left + 1; i <= right; i++) {
                if (sequence[i] > max) {
                    max = sequence[i];
                }
            }
            return max;
        }
        if (n == right) {
            int min = sequence[left];
            for (int i = left + 1; i <= right; i++) {
                if (sequence[i] < min) {
                    min = sequence[i];
                }
            }
            return min;
        }
        int middle = left + ((right - left) >>> 1);
        int x = sequence[middle];
        int i = left;
        int j = right;
        do {
            while (sequence[i] > x) {
                i++;
            }
            while (sequence[j] < x) {
                j--;
            }
            if (i <= j) {
                int temp = sequence[i];
                sequence[i] = sequence[j];
                sequence[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);
        if (i - j == 2 && n == j + 1) {
            return sequence[n];
        }
        if (n <= j) {
            return findNthMaxNumberUsingSelectionSearch(sequence, n, left, j);
        } else {
            return findNthMaxNumberUsingSelectionSearch(sequence, n, i, right);
        }
    }

    public static int findNthMaxNumberUsingBinaryHeap(int[] sequence, int n) {
        if (sequence == null) {
            throw new IllegalArgumentException("Input array is null");
        }
        if (n < 1) {
            throw new IllegalArgumentException("Input n is non-positive");
        }
        if (sequence.length < n) {
            throw new IllegalArgumentException("Array size is less than input n");
        }
        PriorityQueue<Integer> binaryHeap = new PriorityQueue<>(n);
        for (int element : sequence) {
            if (binaryHeap.size() < n) {
                binaryHeap.add(element);
            } else {
                Integer min = binaryHeap.peek();
                if (min < element) {
                    binaryHeap.poll();
                    binaryHeap.add(element);
                }
            }
        }
        return binaryHeap.peek();
    }
}
