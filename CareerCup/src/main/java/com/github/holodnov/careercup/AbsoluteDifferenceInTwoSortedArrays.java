package com.github.holodnov.careercup;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=22808663">http://www.careercup.com/question?id=22808663</a>
 */
public class AbsoluteDifferenceInTwoSortedArrays {

    public static Triple findMinimumDifference(int[] first, int[] second) {
        if (first == null || first.length == 0) {
            throw new IllegalArgumentException("First array is null or empty");
        }
        if (second == null || second.length == 0) {
            throw new IllegalArgumentException("Second array is null or empty");
        }
        if (first.length != second.length) {
            throw new IllegalArgumentException("Arrays have different sizes");
        }
        int diff = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        int posInFirst = 0;
        int posInSecond = 0;
        while (i < first.length && j < second.length) {
            if (diff > Math.abs(first[i] - second[j])) {
                diff = Math.abs(first[i] - second[j]);
                posInFirst = i;
                posInSecond = j;
            }
            if (first[i] <= second[j]) {
                i++;
            } else {
                j++;
            }
        }
        return new Triple(diff, posInFirst, posInSecond);
    }

    public static class Triple {

        public final int first;
        public final int second;
        public final int third;

        public Triple(int first, int second, int third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        @Override
        public int hashCode() {
            int result = first;
            result = 31 * result + second;
            result = 31 * result + third;
            return result;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o == null || o.getClass() != Triple.class) {
                return false;
            }
            Triple t = (Triple) o;
            return (first == t.first && second == t.second && third == t.third);
        }

        @Override
        public String toString() {
            return "Triple{" +
                    "first=" + first +
                    ", second=" + second +
                    ", third=" + third +
                    '}';
        }
    }
}
