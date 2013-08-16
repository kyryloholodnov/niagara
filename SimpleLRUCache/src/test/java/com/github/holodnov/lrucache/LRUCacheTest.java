package com.github.holodnov.lrucache;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

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
public class LRUCacheTest {
    @Test(expected = IllegalArgumentException.class)
    public void testLRUCacheConstructorNegativeMaxSize() {
	new LRUCache<String, Integer>(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLRUCacheConstructorZeroMaxSize() {
	new LRUCache<String, Integer>(0);
    }

    @Test
    public void testLRUCachePut() {
	LRUCache<String, Integer> cache = new LRUCache<String, Integer>(5);
	assertThat(cache.getMaxSize(), is(5));
	cache.put("1", 1);
	cache.put("2", 2);
	cache.put("3", 3);
	cache.put("4", 4);
	cache.put("5", 5);
	assertThat(cache.size(), is(5));
	cache.put("6", 6);
	assertThat(cache.size(), is(5));
	assertNull(cache.get("1"));
	assertThat(cache.getMaxSize(), is(5));
    }

    @Test
    public void testLRUCachePutGet() {
	LRUCache<String, Integer> cache = new LRUCache<String, Integer>(5);
	assertThat(cache.getMaxSize(), is(5));
	cache.put("1", 1);
	cache.put("2", 2);
	cache.put("3", 3);
	cache.put("4", 4);
	cache.put("5", 5);
	assertThat(cache.size(), is(5));
	assertThat(cache.get("1"), is(1));
	cache.put("6", 6);
	assertThat(cache.size(), is(5));
	assertNull(cache.get("2"));
	assertThat(cache.get("1"), is(1));
	assertThat(cache.getMaxSize(), is(5));
    }

    @Test
    public void testLRUCachePutGet2() {
	LRUCache<String, Integer> cache = new LRUCache<String, Integer>(5);
	assertThat(cache.getMaxSize(), is(5));
	cache.put("1", 1);
	cache.put("2", 2);
	cache.put("3", 3);
	cache.put("4", 4);
	cache.put("5", 5);
	assertThat(cache.size(), is(5));
	assertThat(cache.get("2"), is(2));
	assertThat(cache.get("1"), is(1));
	cache.put("6", 6);
	assertThat(cache.size(), is(5));
	assertNull(cache.get("3"));
	assertThat(cache.get("2"), is(2));
	assertThat(cache.get("1"), is(1));
	assertThat(cache.getMaxSize(), is(5));
    }

    @Test
    public void testLRUCachePutGet3() {
	LRUCache<String, Integer> cache = new LRUCache<String, Integer>(5);
	assertThat(cache.getMaxSize(), is(5));
	cache.put("1", 1);
	cache.put("2", 2);
	cache.put("3", 3);
	cache.put("4", 4);
	cache.put("5", 5);
	assertThat(cache.size(), is(5));
	cache.put("6", 6);
	cache.put("7", 7);
	cache.put("8", 8);
	cache.put("9", 9);
	cache.put("10", 10);
	assertNull(cache.get("1"));
	assertNull(cache.get("2"));
	assertNull(cache.get("3"));
	assertNull(cache.get("4"));
	assertNull(cache.get("5"));
	cache.remove("10");
	assertThat(cache.size(), is(4));
	assertThat(cache.getMaxSize(), is(5));
    }

    @Test
    public void testLRUCachePutGet4() {
	LRUCache<String, Integer> cache = new LRUCache<String, Integer>(1);
	assertThat(cache.size(), is(0));
	cache.put("1", 1);
	assertThat(cache.get("1"), is(1));
	cache.put("2", 2);
	assertThat(cache.size(), is(1));
	assertNull(cache.get("1"));
	assertThat(cache.get("2"), is(2));
	assertThat(cache.getMaxSize(), is(1));
    }
}
