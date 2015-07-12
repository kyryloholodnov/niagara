package com.github.holodnov.careercup;

import org.junit.Test;

import java.math.BigInteger;

import static com.github.holodnov.careercup.BinaryTreesCount.findBinaryTreesCount;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=22049663">http://www.careercup.com/question?id=22049663</a>
 */
public class BinaryTreesCountTest {

    @Test(expected = IllegalArgumentException.class)
    public void testFindBinaryTreesCountNegativeN() {
        findBinaryTreesCount(-1);
    }

    @Test
    public void testFindBinaryTrees1() {
        assertThat(findBinaryTreesCount(1), is(BigInteger.valueOf(1)));
    }

    @Test
    public void testFindBinaryTrees2() {
        assertThat(findBinaryTreesCount(2), is(BigInteger.valueOf(2)));
    }

    @Test
    public void testFindBinaryTrees3() {
        assertThat(findBinaryTreesCount(3), is(BigInteger.valueOf(5)));
    }

    @Test
    public void testFindBinaryTrees4() {
        assertThat(findBinaryTreesCount(4), is(BigInteger.valueOf(14)));
    }

    @Test
    public void testFindBinaryTrees5() {
        assertThat(findBinaryTreesCount(14), is(BigInteger.valueOf(2674440)));
    }

    @Test
    public void testFindBinaryTrees6() {
        assertThat(findBinaryTreesCount(20), is(BigInteger.valueOf(6564120420L)));
    }

    @Test
    public void testFindBinaryTrees7() {
        assertThat(findBinaryTreesCount(30), is(BigInteger.valueOf(3814986502092304L)));
    }
}
