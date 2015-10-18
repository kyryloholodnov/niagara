package com.github.holodnov.careercup;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.lang.Math.min;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=5161719076749312">http://www.careercup.com/question?id=5161719076749312</a>
 */
public class MultithreadedSumCalculation {

    public static long getSum(final long[] array, int nThreads) {
        if (array == null) {
            throw new IllegalArgumentException("Input array should be not null");
        }
        if (nThreads <= 0) {
            throw new IllegalArgumentException("Number of threads should be positive");
        }
        if (array.length == 0) {
            return 0;
        } else if (array.length == 1) {
            return array[0];
        }
        nThreads = min(nThreads, array.length);
        final ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        int step = array.length / nThreads;
        if (array.length % nThreads > 0) {
            step++;
        }
        Future<Long>[] futures = new Future[nThreads];
        for (int i = 0; i < nThreads; i++) {
            int fromInclusive = i * step;
            int toExclusive = min(fromInclusive + step, array.length);
            futures[i] = executor.submit(() -> calculate(array, fromInclusive, toExclusive));
        }
        executor.shutdown();
        long totalSum = 0;
        for (int i = 0; i < nThreads; i++) {
            try {
                totalSum += futures[i].get();
            } catch (ExecutionException ex) {
                Throwable t = ex.getCause();
                if (t instanceof RuntimeException) {
                    throw (RuntimeException) t;
                } else {
                    throw (Error) t;
                }
            } catch (InterruptedException ignored) {
                throw new RuntimeException("Execution was interrupted");
            }
        }
        return totalSum;
    }

    private static long calculate(long[] array, int fromInclusive, int toExclusive) {
        long partialSum = 0;
        for (int i = fromInclusive; i < toExclusive; i++) {
            partialSum += array[i];
        }
        return partialSum;
    }
}
