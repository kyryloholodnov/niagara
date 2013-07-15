package com.github.holodnov.careercup;

import java.util.Arrays;

/**
 * 
 * @author Kyrylo Holodnov
 */
public class RadixSort {
    public static void sort(long[] sequence) {
	if ((sequence == null) || (sequence.length == 0)) {
	    return;
	}
	long max = Long.MIN_VALUE;
	for (int i = 0; i < sequence.length; i++) {
	    if (sequence[i] < 0) {
		throw new IllegalArgumentException("Element #" + i
			+ " is negative: " + sequence[i]);
	    }
	    max = Math.max(max, sequence[i]);
	}
	final int module = 1001;
	long exp = 1;
	long[] copy = new long[sequence.length];
	int[] buckets = new int[module];
	while (max / exp > 0) {
	    Arrays.fill(buckets, 0);
	    for (int i = 0; i < sequence.length; i++) {
		buckets[(int) ((sequence[i] / exp) % module)]++;
	    }
	    for (int i = 1; i < buckets.length; i++) {
		buckets[i] += buckets[i - 1];
	    }
	    for (int i = sequence.length - 1; i >= 0; i--) {
		copy[--buckets[(int) ((sequence[i] / exp) % module)]] = sequence[i];
	    }
	    System.arraycopy(copy, 0, sequence, 0, sequence.length);
	    exp *= module;
	}
    }

    public static void sort(int[] sequence) {
	if ((sequence == null) || (sequence.length == 0)) {
	    return;
	}
	int max = Integer.MIN_VALUE;
	for (int i = 0; i < sequence.length; i++) {
	    if (sequence[i] < 0) {
		throw new IllegalArgumentException("Element #" + i
			+ " is negative: " + sequence[i]);
	    }
	    max = Math.max(max, sequence[i]);
	}
	final int module = 1001;
	int exp = 1;
	int[] copy = new int[sequence.length];
	int[] buckets = new int[module];
	while (max / exp > 0) {
	    Arrays.fill(buckets, 0);
	    for (int i = 0; i < sequence.length; i++) {
		buckets[(sequence[i] / exp) % module]++;
	    }
	    for (int i = 1; i < buckets.length; i++) {
		buckets[i] += buckets[i - 1];
	    }
	    for (int i = sequence.length - 1; i >= 0; i--) {
		copy[--buckets[(int) ((sequence[i] / exp) % module)]] = sequence[i];
	    }
	    System.arraycopy(copy, 0, sequence, 0, sequence.length);
	    exp *= module;
	}
    }
}
