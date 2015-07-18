package com.github.holodnov.algorithms.tree;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 */
public class SegmentTreeMinTest {

    @Test(expected = IllegalArgumentException.class)
    public void testSegmentTreeMinConstructorNegativeSize() {
        new SegmentTreeMin(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSegmentTreeMinConstructorZeroSize() {
        new SegmentTreeMin(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSegmentTreeMinIncorrectBounds1() {
        SegmentTreeMin tree = new SegmentTreeMin(100);
        tree.addValue(-1, 10, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSegmentTreeMinIncorrectBounds2() {
        SegmentTreeMin tree = new SegmentTreeMin(100);
        tree.addValue(1, 101, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSegmentTreeMinIncorrectBounds3() {
        SegmentTreeMin tree = new SegmentTreeMin(100);
        tree.addValue(10, 10, 101);
    }

    @Test
    public void testSegmentTreeMin1() {
        SegmentTreeMin tree = new SegmentTreeMin(1);
        tree.addValue(0, 1, 10);
        assertThat(tree.getMinimum(0, 1), is(10L));
        tree.addValue(0, 1, 15);
        assertThat(tree.getMinimum(0, 1), is(25L));
    }

    @Test
    public void testSegmentTreeMin2() {
        SegmentTreeMin tree = new SegmentTreeMin(10);
        tree.addValue(0, 5, 10);
        for (int i = 1; i < 5; i++) {
            assertThat(tree.getMinimum(0, i), is(10L));
        }
        for (int i = 6; i <= 10; i++) {
            assertThat(tree.getMinimum(0, i), is(0L));
        }
        tree.addValue(4, 10, 15);
        assertThat(tree.getMinimum(4, 5), is(25L));
        assertThat(tree.getMinimum(4, 6), is(15L));
        tree.addValue(9, 10, 15);
        assertThat(tree.getMinimum(9, 10), is(30L));
    }
}
