package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.BinaryTreesCount.findBinaryTreesCount;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;

import org.junit.Test;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=22049663">http://www.careercup.com/question?id=22049663</a>
 * 
 * @author Kyrylo Holodnov
 */
public class BinaryTreesCountTest {
    @Test(expected = IllegalArgumentException.class)
    public void testFindBinaryTreesCountNegativeN() {
	findBinaryTreesCount(-1);
    }

    @Test
    public void testFindBinaryTrees1() {
	BigInteger res = findBinaryTreesCount(1);
	assertThat(res, is(BigInteger.valueOf(1)));
    }

    @Test
    public void testFindBinaryTrees2() {
	BigInteger res = findBinaryTreesCount(2);
	assertThat(res, is(BigInteger.valueOf(2)));
    }

    @Test
    public void testFindBinaryTrees3() {
	BigInteger res = findBinaryTreesCount(3);
	assertThat(res, is(BigInteger.valueOf(5)));
    }

    @Test
    public void testFindBinaryTrees4() {
	BigInteger res = findBinaryTreesCount(4);
	assertThat(res, is(BigInteger.valueOf(14)));
    }

    @Test
    public void testFindBinaryTrees5() {
	BigInteger res = findBinaryTreesCount(14);
	assertThat(res, is(BigInteger.valueOf(2674440)));
    }

    @Test
    public void testFindBinaryTrees6() {
	BigInteger res = findBinaryTreesCount(20);
	assertThat(res, is(BigInteger.valueOf(6564120420L)));
    }

    @Test
    public void testFindBinaryTrees7() {
	BigInteger res = findBinaryTreesCount(30);
	assertThat(res, is(BigInteger.valueOf(3814986502092304L)));
    }
}
