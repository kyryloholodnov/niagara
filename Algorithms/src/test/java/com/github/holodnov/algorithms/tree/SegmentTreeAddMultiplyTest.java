package com.github.holodnov.algorithms.tree;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 */
public class SegmentTreeAddMultiplyTest {

    @Test
    public void testSegmentTreeAddMultiply() {
        long[] arr = {1, 3, 2, 5, 6, 8, 7, 9, 5, 2};
        SegmentTreeAddMultiply tree = new SegmentTreeAddMultiply(arr.length, Long::sum, 0L);
        for (int i = 0; i < arr.length; i++) {
            tree.updateAdd(i, i, arr[i]);
        }
        assertThat(tree.query(0, 1), is(4L));
        assertThat(tree.query(2, 4), is(13L));
        assertThat(tree.query(0, 9), is(48L));

        tree.updateMul(0, 4, 2);
        assertThat(tree.query(0, 1), is(8L));
        assertThat(tree.query(2, 4), is(26L));
        assertThat(tree.query(0, 9), is(65L));

        tree.updateAdd(3, 7, 3);
        assertThat(tree.query(0, 1), is(8L));
        assertThat(tree.query(2, 4), is(32L));
        assertThat(tree.query(0, 9), is(80L));
    }
}
