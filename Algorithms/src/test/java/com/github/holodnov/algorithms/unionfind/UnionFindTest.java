package com.github.holodnov.algorithms.unionfind;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Kyrylo Holodnov
 */
public class UnionFindTest {

    @Test(expected = IllegalArgumentException.class)
    public void testFindNegativeInputParam() {
        new UnionFind(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindInputParamIsZero() {
        new UnionFind(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindIllegalArgument1() {
        UnionFind unionFind = new UnionFind(5);
        unionFind.find(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindIllegalArgument2() {
        UnionFind unionFind = new UnionFind(5);
        unionFind.find(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnionIllegalArgument1() {
        UnionFind unionFind = new UnionFind(5);
        unionFind.union(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnionIllegalArgument2() {
        UnionFind unionFind = new UnionFind(5);
        unionFind.union(1, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubsetSizeIllegalArgument1() {
        UnionFind unionFind = new UnionFind(5);
        unionFind.getSubsetSize(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubsetSizeIllegalArgument2() {
        UnionFind unionFind = new UnionFind(5);
        unionFind.getSubsetSize(5);
    }

    @Test
    public void testUnionFindGetSubsetSizeGetSubsetsCount() {
        UnionFind unionFind = new UnionFind(5);
        assertEquals(5, unionFind.getSubsetsCount());
        assertEquals(1, unionFind.getSubsetSize(0));
        assertEquals(1, unionFind.getSubsetSize(4));
        int unionResult = unionFind.union(1, 2);
        assertTrue(unionResult == 1 || unionResult == 2);
        assertEquals(2, unionFind.getSubsetSize(1));
        assertEquals(2, unionFind.getSubsetSize(2));
        assertEquals(1, unionFind.getSubsetSize(4));
        assertEquals(4, unionFind.getSubsetsCount());
        assertTrue(unionFind.find(1) == 1 || unionFind.find(1) == 2);
        assertTrue(unionFind.find(2) == 1 || unionFind.find(2) == 2);
        unionResult = unionFind.union(1, 4);
        assertTrue(unionResult == 1 || unionResult == 2 || unionResult == 4);
        assertEquals(3, unionFind.getSubsetSize(1));
        assertEquals(3, unionFind.getSubsetSize(2));
        assertEquals(1, unionFind.getSubsetSize(3));
        assertEquals(3, unionFind.getSubsetSize(4));
        assertEquals(3, unionFind.getSubsetsCount());
        assertTrue(unionFind.find(1) == 1 || unionFind.find(1) == 2 || unionFind.find(1) == 4);
        assertTrue(unionFind.find(2) == 1 || unionFind.find(2) == 2 || unionFind.find(2) == 4);
        assertTrue(unionFind.find(4) == 1 || unionFind.find(4) == 2 || unionFind.find(4) == 4);
    }
}
