package com.github.holodnov.spinlock;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Kyrylo Holodnov
 */
public class SimpleSpinlockTest {

	private static SimpleSpinlock<Long> simpleSpinlock;

	@BeforeClass
	public static void initSpinlock() {
		simpleSpinlock = new SimpleSpinlock<Long>();
	}

	@Test
	public void testExecuteWithSpinlockReturnLong() throws Exception {
		final int THREAD_COUNT = 10;
		final AtomicInteger cnt = new AtomicInteger();
		final ExecutorService executor = Executors
				.newFixedThreadPool(THREAD_COUNT);
		for (int i = 0; i < THREAD_COUNT; i++) {
			executor.submit(new Callable<Long>() {

				public Long call() throws Exception {
					long res = simpleSpinlock
							.executeWithSpinLock(new CallableWithLong());
					assertThat("Result is not matched", res, is(0L));
					cnt.incrementAndGet();
					return res;
				}
			});
		}
		executor.shutdown();
		executor.awaitTermination(THREAD_COUNT, TimeUnit.SECONDS);
		assertThat("Only " + cnt.get() + " internal threads were executed",
				cnt.get(), is(THREAD_COUNT));
	}

	@Test
	public void testExecuteWithSpinlockThrowsException() throws Exception {
		final int THREAD_COUNT = 10;
		final AtomicInteger cnt = new AtomicInteger();
		final ExecutorService executor = Executors
				.newFixedThreadPool(THREAD_COUNT);
		for (int i = 0; i < THREAD_COUNT; i++) {
			executor.submit(new Callable<Long>() {

				public Long call() throws Exception {
					try {
						simpleSpinlock
								.executeWithSpinLock(new CallableWithException());
						fail("Exception should be thrown in CallableWithException.call() invocation");
					} catch (Exception ex) {

					}
					cnt.incrementAndGet();
					return 0L;
				}
			});
		}
		executor.shutdown();
		executor.awaitTermination(THREAD_COUNT, TimeUnit.SECONDS);
		assertThat("Only " + cnt.get() + " internal threads were executed",
				cnt.get(), is(THREAD_COUNT));
	}

	private static class CallableWithLong implements Callable<Long> {

		public Long call() throws Exception {
			Thread.sleep(100);
			return 0L;
		}

	}

	private static class CallableWithException implements Callable<Long> {

		public Long call() throws Exception {
			Thread.sleep(100);
			throw new Exception("Expectable exception in test");
		}

	}
}
