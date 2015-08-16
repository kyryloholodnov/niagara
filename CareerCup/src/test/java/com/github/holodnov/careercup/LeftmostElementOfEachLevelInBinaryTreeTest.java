package com.github.holodnov.careercup;

import com.github.holodnov.careercup.LeftmostElementOfEachLevelInBinaryTree.BinaryTree;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.github.holodnov.careercup.LeftmostElementOfEachLevelInBinaryTree.findLeftmostElements;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=24157662">http://www.careercup.com/question?id=24157662</a>
 */
public class LeftmostElementOfEachLevelInBinaryTreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void testFindLeftmostElementsNullTree() {
        findLeftmostElements(null);
    }

    @Test
    public void testFindLeftmostElements1() {
        List<Integer> actual = findLeftmostElements(new BinaryTree(null, null, 10));
        List<Integer> expected = Collections.singletonList(10);
        assertThat(actual, is(expected));
    }

    @Test
    public void testFindLeftmostElements2() {
        BinaryTree level21 = new BinaryTree(null, null, 21);
        BinaryTree level22 = new BinaryTree(null, null, 22);
        BinaryTree level23 = new BinaryTree(null, null, 23);
        BinaryTree level24 = new BinaryTree(null, null, 24);
        BinaryTree level11 = new BinaryTree(level21, level22, 11);
        BinaryTree level12 = new BinaryTree(level23, level24, 12);
        BinaryTree root = new BinaryTree(level11, level12, 1);
        List<Integer> actual = findLeftmostElements(root);
        List<Integer> expected = Arrays.asList(1, 11, 21);
        assertThat(actual, is(expected));
    }

    @Test
    public void testFindLeftmostElements3() {
        BinaryTree level32 = new BinaryTree(null, null, 32);
        BinaryTree level34 = new BinaryTree(null, null, 34);
        BinaryTree level21 = new BinaryTree(null, null, 21);
        BinaryTree level22 = new BinaryTree(level34, null, 22);
        BinaryTree level23 = new BinaryTree(null, null, 23);
        BinaryTree level24 = new BinaryTree(level32, null, 24);
        BinaryTree level11 = new BinaryTree(level21, level22, 11);
        BinaryTree level12 = new BinaryTree(level23, level24, 12);
        BinaryTree root = new BinaryTree(level11, level12, 1);
        List<Integer> actual = findLeftmostElements(root);
        List<Integer> expected = Arrays.asList(1, 11, 21, 34);
        assertThat(actual, is(expected));
    }
}
