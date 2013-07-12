package com.github.holodnov.spinlock;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 
 * @author Kyrylo Holodnov
 */
public class SimpleSpinlock<V> {

	private final AtomicBoolean lock = new AtomicBoolean(false);

	public V executeWithSpinLock(Callable<V> e) throws Exception {
		while (true) {
			// Busy non-CAS waiting until lock will be free.
			while (alreadyLocked()) {
			}
			if (!lock()) {
				// We didn’t acquire lock… another thread did it before us.
				continue;
			}
			// We took the lock. Let’s do the work and unlock in finally-block.
			try {
				return e.call();
			} finally {
				unlock();
			}
		}
	}

	private boolean alreadyLocked() {
		return lock.get();
	}

	private boolean lock() {
		return lock.compareAndSet(false, true);
	}

	private void unlock() {
		lock.compareAndSet(true, false);
	}
}