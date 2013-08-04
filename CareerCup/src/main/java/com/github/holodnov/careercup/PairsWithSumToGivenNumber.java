package com.github.holodnov.careercup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=24514662">http://www.careercup.com/question?id=24514662</a>
 * 
 * @author Kyrylo Holodnov
 */
public class PairsWithSumToGivenNumber {

    public static ArrayList<Pair> getPairsWithSumToGivenNumber(int[] sequence,
	    int n) {
	if (sequence == null) {
	    throw new IllegalArgumentException("Input array is null");
	}
	ArrayList<Pair> res = new ArrayList<Pair>();
	Set<Integer> sequenceSet = new HashSet<Integer>();
	boolean firstNdiv2 = false;
	boolean secondNdiv2 = false;
	for (int i = 0; i < sequence.length; i++) {
	    if (((n & 1) == 0) && (sequence[i] == (n >> 1))) {
		if ((firstNdiv2) && (secondNdiv2)) {
		    continue;
		}
		if (!firstNdiv2) {
		    firstNdiv2 = true;
		    continue;
		} else {
		    secondNdiv2 = true;
		    res.add(new Pair(n >> 1, n >> 1));
		    continue;
		}
	    }
	    boolean added = sequenceSet.add(sequence[i]);
	    if (!added) {
		continue;
	    }
	    if (sequenceSet.contains(n - sequence[i])) {
		res.add(new Pair(n - sequence[i], sequence[i]));
	    }
	}
	return res;
    }

    public static class Pair {
	public final int first;
	public final int second;

	public Pair(int first, int second) {
	    this.first = first;
	    this.second = second;
	}

	public int hashCode() {
	    return 41 * first + 47 * second;
	}

	public boolean equals(Object o) {
	    if (o == this) {
		return true;
	    }
	    if ((o == null) || (o.getClass() != Pair.class)) {
		return false;
	    }
	    Pair p = (Pair) o;
	    return p.first == first && p.second == second;
	}

	public String toString() {
	    return "first = " + first + "; second = " + second;
	}
    }

}
