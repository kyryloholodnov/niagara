package com.github.holodnov.lrucache;

import java.util.LinkedHashMap;

/**
 * Simple LRU cache implementation based on <tt>LinkedHashMap</tt>.
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access this class concurrently, and at least one of the
 * threads modifies cache structurally, it <em>must</em> be synchronized
 * externally.
 * 
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Cache_algorithms#Least_Recently_Used">http://en.wikipedia.org/wiki/Cache_algorithms#Least_Recently_Used</a>
 * 
 * @author Kyrylo Holodnov
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private static final long serialVersionUID = 1L;
    private final int maxSize;

    public LRUCache(int maxSize) {
	super(maxSize <= 0 ? -1 : 4 * (maxSize + 1) / 3 + 1, 0.75f, true);
	this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
	return size() > maxSize;
    }

    public int getMaxSize() {
	return maxSize;
    }
}
