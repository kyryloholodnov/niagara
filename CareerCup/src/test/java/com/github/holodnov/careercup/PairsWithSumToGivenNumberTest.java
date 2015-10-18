package com.github.holodnov.careercup;

import com.github.holodnov.careercup.PairsWithSumToGivenNumber.Pair;
import org.junit.Test;

import java.util.List;

import static com.github.holodnov.careercup.PairsWithSumToGivenNumber.getPairsWithSumToGivenNumber;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=24514662">http://www.careercup.com/question?id=24514662</a>
 */
public class PairsWithSumToGivenNumberTest {

    @Test(expected = IllegalArgumentException.class)
    public void testGetPairsWithSumToGivenNumberNullSequence() {
        getPairsWithSumToGivenNumber(null, 10);
    }

    @Test
    public void testGetPairsWithSumToGivenNumberEmptySequence() {
        assertThat(getPairsWithSumToGivenNumber(new int[0], 10), is(emptyList()));
    }

    @Test
    public void testGetPairsWithSumToGivenNumber1() {
        List<Pair> actual = getPairsWithSumToGivenNumber(new int[]{1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1}, 10);
        List<Pair> expected = asList(new Pair(4, 6), new Pair(5, 5));
        assertThat(actual, is(expected));
    }

    @Test
    public void testGetPairsWithSumToGivenNumber2() {
        List<Pair> actual = getPairsWithSumToGivenNumber(new int[]{1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1}, -10);
        List<Pair> expected = emptyList();
        assertThat(actual, is(expected));
    }

    @Test
    public void testGetPairsWithSumToGivenNumber3() {
        List<Pair> actual = getPairsWithSumToGivenNumber(new int[]{-1, -2, -3, -4, -5, -6, -5, -4, -3, -2, -1}, -10);
        List<Pair> expected = asList(new Pair(-4, -6), new Pair(-5, -5));
        assertThat(actual, is(expected));
    }

    @Test
    public void testGetPairsWithSumToGivenNumber4() {
        List<Pair> actual = getPairsWithSumToGivenNumber(new int[]{-1, -2, -3, -4, -5, -6, -4, -3, -2, -1}, -10);
        List<Pair> expected = singletonList(new Pair(-4, -6));
        assertThat(actual, is(expected));
    }

    @Test
    public void testGetPairsWithSumToGivenNumber5() {
        List<Pair> actual = getPairsWithSumToGivenNumber(new int[]{-1, -2, -3, -4, -5, 2, 3, 4, 5, 6}, 0);
        List<Pair> expected = asList(
                new Pair(-2, 2),
                new Pair(-3, 3),
                new Pair(-4, 4),
                new Pair(-5, 5)
        );
        assertThat(actual, is(expected));
    }

    @Test
    public void testGetPairsWithSumToGivenNumber6() {
        List<Pair> actual = getPairsWithSumToGivenNumber(new int[]{-1, -2, -3, -4, -5, 2, 3, 4, 5, 0, 6}, 0);
        List<Pair> expected = asList(
                new Pair(-2, 2),
                new Pair(-3, 3),
                new Pair(-4, 4),
                new Pair(-5, 5)
        );
        assertThat(actual, is(expected));
    }

    @Test
    public void testGetPairsWithSumToGivenNumber7() {
        List<Pair> actual = getPairsWithSumToGivenNumber(new int[]{0, -1, -2, -3, -4, -5, 2, 3, 4, 5, 0, 6}, 0);
        List<Pair> expected = asList(
                new Pair(-2, 2),
                new Pair(-3, 3),
                new Pair(-4, 4),
                new Pair(-5, 5),
                new Pair(0, 0)
        );
        assertThat(actual, is(expected));
    }

    @Test
    public void testGetPairsWithSumToGivenNumber8() {
        List<Pair> actual = getPairsWithSumToGivenNumber(new int[]{0, -1, -2, -3, -4, -5, 2, 3, 4, 5, 0, 6, 9, 0, -3, 3, -2, 2}, 0);
        List<Pair> expected = asList(
                new Pair(-2, 2),
                new Pair(-3, 3),
                new Pair(-4, 4),
                new Pair(-5, 5),
                new Pair(0, 0)
        );
        assertThat(actual, is(expected));
    }

    @Test
    public void testGetPairsWithSumToGivenNumber9() {
        List<Pair> actual = getPairsWithSumToGivenNumber(new int[]{0, -1, -2, -3, -4, -5, 2, 3, 4, 5, 0, 6, 9, 0, -3, 3, -2, 2}, 1);
        List<Pair> expected = asList(
                new Pair(-1, 2),
                new Pair(-2, 3),
                new Pair(-3, 4),
                new Pair(-4, 5),
                new Pair(-5, 6)
        );
        assertThat(actual, is(expected));
    }
}
