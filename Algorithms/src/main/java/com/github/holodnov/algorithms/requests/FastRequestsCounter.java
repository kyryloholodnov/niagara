package com.github.holodnov.algorithms.requests;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.sqrt;
import static java.lang.System.nanoTime;
import static java.util.concurrent.TimeUnit.*;

/**
 * @author Kyrylo Holodnov
 */
public class FastRequestsCounter {

    private final int maxSize;
    private final int bucketSize;
    private final long keepAliveInterval;
    private final Deque<Deque<Long>> expirations;

    private int currentSize;

    public FastRequestsCounter(long period, TimeUnit unit, int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Input max size should be positive");
        }
        if (period <= 0) {
            throw new IllegalArgumentException("Input period should be positive");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Input time unit should be not null");
        }
        this.maxSize = maxSize;
        bucketSize = (int) sqrt(maxSize);
        keepAliveInterval = NANOSECONDS.convert(period, unit);
        expirations = new ArrayDeque<>((maxSize - 2) / bucketSize + 3);
    }

    public static FastRequestsCounter getSecondRequestsCounter(int maxSize) {
        return new FastRequestsCounter(1, SECONDS, maxSize);
    }

    public static FastRequestsCounter getMinuteRequestsCounter(int maxSize) {
        return new FastRequestsCounter(1, MINUTES, maxSize);
    }

    public static FastRequestsCounter getHourlyRequestsCounter(int maxSize) {
        return new FastRequestsCounter(1, HOURS, maxSize);
    }

    public static FastRequestsCounter getDailyRequestsCounter(int maxSize) {
        return new FastRequestsCounter(1, DAYS, maxSize);
    }

    public static FastRequestsCounter getWeeklyRequestsCounter(int maxSize) {
        return new FastRequestsCounter(7, DAYS, maxSize);
    }

    public synchronized void newRequest() {
        if (currentSize % bucketSize == 1 || currentSize == maxSize) {
            shrinkExpirations();
            if (currentSize == maxSize) {
                removeEldestExpiration();
            }
        }
        addNewExpiration();
    }

    public synchronized int getRequestsCount() {
        if (currentSize == 0) {
            return currentSize;
        }
        shrinkExpirations();
        return currentSize;
    }

    public synchronized void reset() {
        expirations.clear();
        currentSize = 0;
    }

    private void shrinkExpirations() {
        long now = nanoTime();
        while (true) {
            Deque<Long> eldest = expirations.peekFirst();
            if (eldest == null || eldest.peekFirst() >= now) {
                return;
            }
            if (eldest.peekLast() < now) {
                expirations.pollFirst();
                currentSize -= eldest.size();
            } else {
                while (eldest.peekFirst() < now) {
                    eldest.pollFirst();
                    currentSize--;
                }
                return;
            }
        }
    }

    private void removeEldestExpiration() {
        Deque<Long> eldest = expirations.peekFirst();
        eldest.pollFirst();
        if (eldest.isEmpty()) {
            expirations.pollFirst();
        }
        currentSize--;
    }

    private void addNewExpiration() {
        Deque<Long> newest = expirations.peekLast();
        if (newest == null || newest.size() == bucketSize) {
            newest = new ArrayDeque<>(bucketSize);
            expirations.addLast(newest);
        }
        newest.addLast(nanoTime() + keepAliveInterval);
        currentSize++;
    }
}
