package com.github.holodnov.careercup;

import org.junit.Test;

import static com.github.holodnov.careercup.MajorityVoteAlgorithm.findMajorityElement;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=5200260502650880">http://www.careercup.com/question?id=5200260502650880</a>
 */
public class MajorityVoteAlgorithmTest {

    @Test(expected = IllegalArgumentException.class)
    public void testFindMajorityElementNullArray() {
        findMajorityElement(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindMajorityElementEmptyArray() {
        findMajorityElement(new int[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindMajorityElementNoMajorityElement() {
        findMajorityElement(new int[]{1, 2, 3, 4, 5, 1, 1, 1, 3});
    }

    @Test
    public void testFindMajorityElement1() {
        assertThat(findMajorityElement(new int[]{10}), is(10));
    }

    @Test
    public void testFindMajorityElement2() {
        assertThat(findMajorityElement(new int[]{9, 10}), is(9));
    }

    @Test
    public void testFindMajorityElement3() {
        assertThat(findMajorityElement(new int[]{9, 10, 10, 11}), is(10));
    }

    @Test
    public void testFindMajorityElement4() {
        assertThat(findMajorityElement(new int[]{1, 2, 1, 4, 1, 1, 2, 1, 3}), is(1));
    }

    @Test
    public void testFindMajorityElement5() {
        assertThat(findMajorityElement(new int[]{5, 5, 2, 3, 4, 5, 6, 5, 7, 5, 5, 5, 3}), is(5));
    }
}
