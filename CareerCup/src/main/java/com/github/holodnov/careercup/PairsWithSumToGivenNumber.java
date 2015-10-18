package com.github.holodnov.careercup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=24514662">http://www.careercup.com/question?id=24514662</a>
 */
public class PairsWithSumToGivenNumber {

    public static List<Pair> getPairsWithSumToGivenNumber(int[] sequence, int n) {
        if (sequence == null) {
            throw new IllegalArgumentException("Input array is null");
        }
        ArrayList<Pair> pairs = new ArrayList<>();
        Set<Integer> elementsSet = new HashSet<>();
        boolean firstNDiv2 = false;
        boolean secondNDiv2 = false;
        for (int element : sequence) {
            if ((n & 1) == 0 && element == (n >> 1)) {
                if (firstNDiv2 && secondNDiv2) {
                    continue;
                }
                if (!firstNDiv2) {
                    firstNDiv2 = true;
                    continue;
                } else {
                    secondNDiv2 = true;
                    pairs.add(new Pair(n >> 1, n >> 1));
                    continue;
                }
            }
            boolean added = elementsSet.add(element);
            if (!added) {
                continue;
            }
            if (elementsSet.contains(n - element)) {
                pairs.add(new Pair(n - element, element));
            }
        }
        return pairs;
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
            if (o == null || o.getClass() != Pair.class) {
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
