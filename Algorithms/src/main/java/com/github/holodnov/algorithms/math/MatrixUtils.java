package com.github.holodnov.algorithms.math;

public class MatrixUtils {

    public static final long p = 1000000007;

    public static long[] multiply(long[][] m, long[] vec) {
        long[] c = new long[vec.length];
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                c[i] += m[i][j] * vec[j];
                c[i] %= p;
            }
        }
        return c;
    }

    public static long[][] deg(long[][] a, long n) {
        if (n == 1) {
            return a;
        }
        long[][] sqr = multiply(a, a);
        if (n % 2 == 0) {
            return deg(sqr, n / 2);
        } else {
            return multiply(deg(sqr, n / 2), a);
        }
    }

    public static long[][] multiply(long[][] a, long[][] b) {
        long[][] c = new long[a.length][b[0].length];
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[i].length; j++) {
                c[i][j] = 0;
                for (int k = 0; k < a[0].length; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                    c[i][j] %= p;
                }
            }
        }
        return c;
    }
}
