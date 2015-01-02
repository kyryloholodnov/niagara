package com.github.holodnov.algorithms.requests;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

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
        bucketSize = (int) Math.sqrt(maxSize);
        keepAliveInterval = TimeUnit.NANOSECONDS.convert(period, unit);
        expirations = new LinkedList<>();
    }

    public static FastRequestsCounter getSecondRequestsCounter(int maxSize) {
        return new FastRequestsCounter(1, TimeUnit.SECONDS, maxSize);
    }

    public static FastRequestsCounter getMinuteRequestsCounter(int maxSize) {
        return new FastRequestsCounter(1, TimeUnit.MINUTES, maxSize);
    }

    public static FastRequestsCounter getHourlyRequestsCounter(int maxSize) {
        return new FastRequestsCounter(1, TimeUnit.HOURS, maxSize);
    }

    public static FastRequestsCounter getDailyRequestsCounter(int maxSize) {
        return new FastRequestsCounter(1, TimeUnit.DAYS, maxSize);
    }

    public static FastRequestsCounter getWeeklyRequestsCounter(int maxSize) {
        return new FastRequestsCounter(7, TimeUnit.DAYS, maxSize);
    }

    public synchronized void newRequest() {
        if (currentSize == maxSize || currentSize % bucketSize == 0) {
            shrinkExpirations();
            if (currentSize == maxSize) {
                removeEldestExpiration();
            }
        }
        addNewExpiration();
    }

    public synchronized int getRequestsCount() {
        shrinkExpirations();
        return currentSize;
    }

    public synchronized void reset() {
        expirations.clear();
        currentSize = 0;
    }

    private void shrinkExpirations() {
        long now = System.nanoTime();
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
            newest = new LinkedList<>();
            expirations.addLast(newest);
        }
        newest.addLast(System.nanoTime() + keepAliveInterval);
        currentSize++;
    }
}
