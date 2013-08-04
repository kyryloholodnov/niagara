package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.PairsWithSumToGivenNumber.getPairsWithSumToGivenNumber;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.Test;

import com.github.holodnov.careercup.PairsWithSumToGivenNumber.Pair;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=24514662">http://www.careercup.com/question?id=24514662</a>
 * 
 * @author Kyrylo Holodnov
 */
public class PairsWithSumToGivenNumberTest {
    @Test(expected = IllegalArgumentException.class)
    public void testGetPairsWithSumToGivenNumberNullSequence() {
	getPairsWithSumToGivenNumber(null, 10);
    }

    @Test
    public void testGetPairsWithSumToGivenNumberEmptySequence() {
	assertThat(getPairsWithSumToGivenNumber(new int[0], 10),
		is(new ArrayList<Pair>()));
    }

    @Test
    public void testGetPairsWithSumToGivenNumber1() {
	ArrayList<Pair> actual = getPairsWithSumToGivenNumber(new int[] { 1, 2,
		3, 4, 5, 6, 5, 4, 3, 2, 1 }, 10);
	ArrayList<Pair> expected = new ArrayList<Pair>() {
	    {
		add(new Pair(4, 6));
		add(new Pair(5, 5));
	    }
	};
	assertThat(actual, is(expected));
    }

    @Test
    public void testGetPairsWithSumToGivenNumber2() {
	ArrayList<Pair> actual = getPairsWithSumToGivenNumber(new int[] { 1, 2,
		3, 4, 5, 6, 5, 4, 3, 2, 1 }, -10);
	ArrayList<Pair> expected = new ArrayList<Pair>();
	assertThat(actual, is(expected));
    }

    @Test
    public void testGetPairsWithSumToGivenNumber3() {
	ArrayList<Pair> actual = getPairsWithSumToGivenNumber(new int[] { -1,
		-2, -3, -4, -5, -6, -5, -4, -3, -2, -1 }, -10);
	ArrayList<Pair> expected = new ArrayList<Pair>() {
	    {
		add(new Pair(-4, -6));
		add(new Pair(-5, -5));
	    }
	};
	assertThat(actual, is(expected));
    }

    @Test
    public void testGetPairsWithSumToGivenNumber4() {
	ArrayList<Pair> actual = getPairsWithSumToGivenNumber(new int[] { -1,
		-2, -3, -4, -5, -6, -4, -3, -2, -1 }, -10);
	ArrayList<Pair> expected = new ArrayList<Pair>() {
	    {
		add(new Pair(-4, -6));
	    }
	};
	assertThat(actual, is(expected));
    }

    @Test
    public void testGetPairsWithSumToGivenNumber5() {
	ArrayList<Pair> actual = getPairsWithSumToGivenNumber(new int[] { -1,
		-2, -3, -4, -5, 2, 3, 4, 5, 6 }, 0);
	ArrayList<Pair> expected = new ArrayList<Pair>() {
	    {
		add(new Pair(-2, 2));
		add(new Pair(-3, 3));
		add(new Pair(-4, 4));
		add(new Pair(-5, 5));
	    }
	};
	assertThat(actual, is(expected));
    }

    @Test
    public void testGetPairsWithSumToGivenNumber6() {
	ArrayList<Pair> actual = getPairsWithSumToGivenNumber(new int[] { -1,
		-2, -3, -4, -5, 2, 3, 4, 5, 0, 6 }, 0);
	ArrayList<Pair> expected = new ArrayList<Pair>() {
	    {
		add(new Pair(-2, 2));
		add(new Pair(-3, 3));
		add(new Pair(-4, 4));
		add(new Pair(-5, 5));
	    }
	};
	assertThat(actual, is(expected));
    }

    @Test
    public void testGetPairsWithSumToGivenNumber7() {
	ArrayList<Pair> actual = getPairsWithSumToGivenNumber(new int[] { 0,
		-1, -2, -3, -4, -5, 2, 3, 4, 5, 0, 6 }, 0);
	ArrayList<Pair> expected = new ArrayList<Pair>() {
	    {
		add(new Pair(-2, 2));
		add(new Pair(-3, 3));
		add(new Pair(-4, 4));
		add(new Pair(-5, 5));
		add(new Pair(0, 0));
	    }
	};
	assertThat(actual, is(expected));
    }

    @Test
    public void testGetPairsWithSumToGivenNumber8() {
	ArrayList<Pair> actual = getPairsWithSumToGivenNumber(new int[] { 0,
		-1, -2, -3, -4, -5, 2, 3, 4, 5, 0, 6, 9, 0, -3, 3, -2, 2 }, 0);
	ArrayList<Pair> expected = new ArrayList<Pair>() {
	    {
		add(new Pair(-2, 2));
		add(new Pair(-3, 3));
		add(new Pair(-4, 4));
		add(new Pair(-5, 5));
		add(new Pair(0, 0));
	    }
	};
	assertThat(actual, is(expected));
    }

    @Test
    public void testGetPairsWithSumToGivenNumber9() {
	ArrayList<Pair> actual = getPairsWithSumToGivenNumber(new int[] { 0,
		-1, -2, -3, -4, -5, 2, 3, 4, 5, 0, 6, 9, 0, -3, 3, -2, 2 }, 1);
	ArrayList<Pair> expected = new ArrayList<Pair>() {
	    {
		add(new Pair(-1, 2));
		add(new Pair(-2, 3));
		add(new Pair(-3, 4));
		add(new Pair(-4, 5));
		add(new Pair(-5, 6));
	    }
	};
	assertThat(actual, is(expected));
    }
}
