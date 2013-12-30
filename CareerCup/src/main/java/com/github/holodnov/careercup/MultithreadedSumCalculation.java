package com.github.holodnov.careercup;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=5161719076749312">http://www.careercup.com/question?id=5161719076749312</a>
 * 
 * @author Kyrylo Holodnov
 */
public class MultithreadedSumCalculation {

    public static long getSum(final long[] array, int nThreads) {
	if (array == null) {
	    throw new IllegalArgumentException("Input array should be not null");
	}
	if (nThreads <= 0) {
	    throw new IllegalArgumentException(
		    "Number of threads should be positive");
	}
	if (array.length == 0) {
	    return 0;
	} else if (array.length == 1) {
	    return array[0];
	}
	nThreads = Math.min(nThreads, array.length);
	final CountDownLatch gate = new CountDownLatch(nThreads);
	final long[] partialSums = new long[nThreads];
	final ExecutorService executor = Executors.newFixedThreadPool(nThreads);
	int step = array.length / nThreads;
	if ((array.length % nThreads) > 0) {
	    step++;
	}
	for (int i = 0; i < nThreads; i++) {
	    final int index = i;
	    final int fromInclusive = index * step;
	    final int toExclusive = Math
		    .min(fromInclusive + step, array.length);
	    executor.execute(new Runnable() {
		public void run() {
		    try {
			partialSums[index] = calculate(array, fromInclusive,
				toExclusive);
		    } finally {
			gate.countDown();
		    }
		}
	    });
	}
	executor.shutdown();
	try {
	    gate.await();
	} catch (InterruptedException ignored) {
	}
	long result = 0;
	for (int i = 0; i < nThreads; i++) {
	    result += partialSums[i];
	}
	return result;
    }

    private static long calculate(long[] array, int fromInclusive,
	    int toExclusive) {
	long result = 0;
	for (int i = fromInclusive; i < toExclusive; i++) {
	    result += array[i];
	}
	return result;
    }

}
