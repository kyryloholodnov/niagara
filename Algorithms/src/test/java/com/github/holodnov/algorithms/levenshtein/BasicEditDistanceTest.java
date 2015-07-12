package com.github.holodnov.algorithms.levenshtein;

import org.junit.Test;

import static com.github.holodnov.algorithms.levenshtein.BasicEditDistance.getEditDistance;
import static org.junit.Assert.assertEquals;

/**
 * @author Kyrylo Holodnov
 */
public class BasicEditDistanceTest {

    private static final double EPSILON = 0.0000001;
    private static final OperationsWeights hugeSubstitutionOperationsWeights = new OperationsWeights() {

        @Override
        public double getSubstitutionWeight(char oldChar, char newChar) {
            return 1.5;
        }
    };

    @Test(expected = IllegalArgumentException.class)
    public void testGetEditDistanceFirstIsNull() {
        getEditDistance(null, "abc");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetEditDistanceSecondIsNull() {
        getEditDistance("abc", null);
    }

    @Test
    public void testGetEditDistance1() {
        CharSequence s = "abc";
        CharSequence t = "";
        assertEquals(3.0, getEditDistance(s, t), EPSILON);
        assertEquals(3.0, getEditDistance(t, s), EPSILON);
    }

    @Test
    public void testGetEditDistance2() {
        CharSequence s = "abc";
        CharSequence t = "b";
        assertEquals(2.0, getEditDistance(s, t), EPSILON);
        assertEquals(2.0, getEditDistance(t, s), EPSILON);
    }

    @Test
    public void testGetEditDistance3() {
        CharSequence s = "abc";
        CharSequence t = "abc";
        assertEquals(0.0, getEditDistance(s, t), EPSILON);
        assertEquals(0.0, getEditDistance(t, s), EPSILON);
    }

    @Test
    public void testGetEditDistance4() {
        CharSequence s = "abc";
        CharSequence t = "dce";
        assertEquals(3.0, getEditDistance(s, t), EPSILON);
        assertEquals(3.0, getEditDistance(t, s), EPSILON);
    }

    @Test
    public void testGetEditDistance5() {
        CharSequence s = "abc";
        CharSequence t = "dec";
        assertEquals(2.0, getEditDistance(s, t), EPSILON);
        assertEquals(2.0, getEditDistance(t, s), EPSILON);
    }

    @Test
    public void testGetEditDistance6() {
        CharSequence s = "abc";
        CharSequence t = "dce";
        assertEquals(3.5, getEditDistance(s, t, hugeSubstitutionOperationsWeights), EPSILON);
        assertEquals(3.5, getEditDistance(t, s, hugeSubstitutionOperationsWeights), EPSILON);
    }

    @Test
    public void testGetEditDistance7() {
        CharSequence s = "abcdefg";
        CharSequence t = "xbcdefg";
        assertEquals(1.5, getEditDistance(s, t, hugeSubstitutionOperationsWeights), EPSILON);
        assertEquals(1.5, getEditDistance(t, s, hugeSubstitutionOperationsWeights), EPSILON);
    }

    @Test
    public void testGetEditDistance8() {
        CharSequence s = "abcdefg";
        CharSequence t = "xbcdeg";
        assertEquals(2.5, getEditDistance(s, t, hugeSubstitutionOperationsWeights), EPSILON);
        assertEquals(2.5, getEditDistance(t, s, hugeSubstitutionOperationsWeights), EPSILON);
    }
}
