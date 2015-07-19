package com.github.holodnov.careercup;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=5977321018228736">http://www.careercup.com/question?id=5977321018228736</a>
 */
public class JosephusProblem {

    public static int getLastPerson(int n, int k) {
        if (n <= 0 || k <= 0) {
            throw new IllegalArgumentException("Input arguments should be positive");
        }
        int lastPerson = 0;
        for (int i = 2; i <= n; i++) {
            lastPerson += k;
            lastPerson %= i;
        }
        return lastPerson;
    }
}
