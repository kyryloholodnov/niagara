package com.github.holodnov.algorithms.tree;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 */
public class SegmentTreeSumTest {

    @Test(expected = IllegalArgumentException.class)
    public void testSegmentTreeSumConstructorNegativeSize() {
        new SegmentTreeSum(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSegmentTreeSumConstructorZeroSize() {
        new SegmentTreeSum(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSegmentTreeSumIncorrectBounds1() {
        SegmentTreeSum tree = new SegmentTreeSum(100);
        tree.addValue(-1, 10, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSegmentTreeSumIncorrectBounds2() {
        SegmentTreeSum tree = new SegmentTreeSum(100);
        tree.addValue(1, 101, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSegmentTreeSumIncorrectBounds3() {
        SegmentTreeSum tree = new SegmentTreeSum(100);
        tree.addValue(10, 10, 101);
    }

    @Test
    public void testSegmentTreeSum1() {
        SegmentTreeSum tree = new SegmentTreeSum(1);
        tree.addValue(0, 1, 10);
        assertThat(tree.getSum(0, 1), is(10L));
        tree.addValue(0, 1, 15);
        assertThat(tree.getSum(0, 1), is(25L));
    }

    @Test
    public void testSegmentTreeSum2() {
        SegmentTreeSum tree = new SegmentTreeSum(10);
        tree.addValue(0, 5, 10);
        for (int i = 1; i < 5; i++) {
            assertThat(tree.getSum(0, i), is(i * 10L));
        }
        for (int i = 6; i <= 10; i++) {
            assertThat(tree.getSum(0, i), is(50L));
        }
        tree.addValue(4, 10, 15);
        assertThat(tree.getSum(4, 5), is(25L));
        assertThat(tree.getSum(4, 6), is(40L));
        tree.addValue(9, 10, 15);
        assertThat(tree.getSum(9, 10), is(30L));
    }
}
