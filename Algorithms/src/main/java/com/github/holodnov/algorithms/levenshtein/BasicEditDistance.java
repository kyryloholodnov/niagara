package com.github.holodnov.algorithms.levenshtein;

import static java.lang.Math.min;

/**
 * A <tt>BasicEditDistance</tt> contains 2 static methods for calculating edit
 * distance between strings (in general - sequences of chars). Time complexity
 * is
 * <b>O(mn)</b> and space complexity is <b>O(min(m,n))</b>, where <b>m</b> and
 * <b>n</b> are the lengths of the strings.
 *
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://en.wikipedia.org/wiki/Edit_distance">http://en.wikipedia.org/wiki/Edit_distance</a>
 */
public final class BasicEditDistance {

    private static final OperationsWeights DEFAULT_OPERATIONS_WEIGHTS = new OperationsWeights() {
    };

    private BasicEditDistance() {
    }

    /**
     * Calculates edit distance between 2 char sequences and default operations
     * weights.
     *
     * @param s 1st sequence of chars
     * @param t 2nd sequence of chars
     * @return edit distance
     * @throws IllegalArgumentException if any sequence of chars is null
     */
    public static double getEditDistance(CharSequence s, CharSequence t) {
        return getEditDistance(s, t, DEFAULT_OPERATIONS_WEIGHTS);
    }

    /**
     * Calculates edit distance between 2 char sequences and given operations
     * weights.
     *
     * @param s                 1st sequence of chars
     * @param t                 2nd sequence of chars
     * @param operationsWeights operations weights object
     * @return edit distance
     * @throws IllegalArgumentException if any sequence of chars is null or
     *                                  operations weights object is null
     */
    public static double getEditDistance(CharSequence s,
                                         CharSequence t,
                                         OperationsWeights operationsWeights) {
        if (s == null) {
            throw new IllegalArgumentException("First sequence of chars is null");
        }
        if (t == null) {
            throw new IllegalArgumentException("Second sequence of chars is null");
        }
        if (operationsWeights == null) {
            throw new IllegalArgumentException("Operations weights object is null");
        }
        // We are accepting that getEditDistance(s, t, X) = getEditDistance(t, s, X)
        if (s.length() > t.length()) {
            CharSequence temp = s;
            s = t;
            t = temp;
        }
        double[] previous = new double[s.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            previous[i] = previous[i - 1] + operationsWeights.getDeletionWeight(s.charAt(i - 1));
        }
        double[] now = new double[s.length() + 1];
        for (int i = 1; i <= t.length(); i++) {
            char tc = t.charAt(i - 1);
            now[0] = previous[0] + operationsWeights.getInsertionWeight(tc);
            for (int j = 1; j <= s.length(); j++) {
                char sc = s.charAt(j - 1);
                if (tc == sc) {
                    now[j] = previous[j - 1];
                } else {
                    double min = now[j - 1] + operationsWeights.getDeletionWeight(sc);
                    min = min(min, previous[j] + operationsWeights.getInsertionWeight(tc));
                    min = min(min, previous[j - 1] + operationsWeights.getSubstitutionWeight(sc, tc));
                    now[j] = min;
                }
            }
            // Swapping references of these 2 arrays
            double[] temp = previous;
            previous = now;
            now = temp;
        }
        return previous[s.length()];
    }
}
