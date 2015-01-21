package com.github.holodnov.algorithms.requests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static com.github.holodnov.algorithms.requests.FastRequestsCounter.getSecondRequestsCounter;

/**
 * @author Kyrylo Holodnov
 */
@RunWith(Parameterized.class)
public class FastRequestsCounterTest {

    private final int maxSize;

    public FastRequestsCounterTest(int maxSize) {
        this.maxSize = maxSize;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(new Object[]{1},
                new Object[]{2},
                new Object[]{3},
                new Object[]{4},
                new Object[]{5},
                new Object[]{10},
                new Object[]{100},
                new Object[]{10000},
                new Object[]{10001},
                new Object[]{100000});
    }

    @Test
    public void testManyRequests() {
        FastRequestsCounter counter = getSecondRequestsCounter(maxSize);
        for (int i = 0; i < maxSize; i++) {
            counter.newRequest();
            assertThat("Many requests are not passed for max size = "
                            + maxSize + " (for i = " + i + ')',
                    counter.getRequestsCount(),
                    is(i + 1));
        }
    }

    @Test
    public void testManyRequestsAfterSleeping() throws InterruptedException {
        FastRequestsCounter counter = getSecondRequestsCounter(maxSize);
        for (int i = 0; i < maxSize; i++) {
            counter.newRequest();
            assertThat("Many requests are not passed for max size = "
                            + maxSize + " (for i = " + i + ')',
                    counter.getRequestsCount(),
                    is(i + 1));
        }
        Thread.sleep(1100);
        assertThat("Many requests are not passed for max size = " + maxSize + " (0 expected after sleeping)",
                counter.getRequestsCount(),
                is(0));
    }

    @Test
    public void testTooManyRequests() {
        FastRequestsCounter counter = getSecondRequestsCounter(maxSize);
        for (int i = 0; i < 3 * maxSize; i++) {
            counter.newRequest();
            assertThat("Too many requests are not passed for max size = "
                            + maxSize + " (for i = " + i + ')',
                    counter.getRequestsCount(),
                    is(Math.min(i + 1, maxSize)));
        }
    }

    @Test
    public void testTooManyRequestsAfterSleeping() throws InterruptedException {
        FastRequestsCounter counter = getSecondRequestsCounter(maxSize);
        for (int i = 0; i < 3 * maxSize; i++) {
            counter.newRequest();
            assertThat("Too many requests are not passed for max size = "
                            + maxSize + " (for i = " + i + ')',
                    counter.getRequestsCount(),
                    is(Math.min(i + 1, maxSize)));
        }
        Thread.sleep(1100);
        assertThat("Too many requests are not passed for max size = " + maxSize + " (0 expected after sleeping)",
                counter.getRequestsCount(),
                is(0));
    }

    @Test
    public void testPerformanceOfManyRequests() throws InterruptedException {
        final ExecutorService executor = newFixedThreadPool(Math.min(10, maxSize));
        final long start = System.currentTimeMillis();
        final FastRequestsCounter counter = getSecondRequestsCounter(maxSize);
        for (int i = 0; i < maxSize; i++) {
            executor.submit(counter::newRequest);
        }
        executor.shutdown();
        executor.awaitTermination(2, SECONDS);
        System.out.println(maxSize + " requests (performance test) for " + maxSize + "-size counter: "
                + (System.currentTimeMillis() - start + 0d) / 1000 + " seconds");
        assertThat("Many requests (performance test) are not passed for max size = " + maxSize,
                counter.getRequestsCount(),
                is(maxSize));
    }

    @Test
    public void testPerformanceOfTooManyRequests() throws InterruptedException {
        final ExecutorService executor = newFixedThreadPool(Math.min(10, maxSize));
        final long start = System.currentTimeMillis();
        final FastRequestsCounter counter = getSecondRequestsCounter(maxSize);
        for (int i = 0; i < 3 * maxSize; i++) {
            executor.submit(counter::newRequest);
        }
        executor.shutdown();
        executor.awaitTermination(2, SECONDS);
        System.out.println((3 * maxSize) + " requests (performance test) for " + maxSize + "-size counter: "
                + (System.currentTimeMillis() - start + 0.0) / 1000 + " seconds");
        assertThat("Too many requests (performance test) are not passed for max size = " + maxSize,
                counter.getRequestsCount(),
                is(maxSize));
    }
}
