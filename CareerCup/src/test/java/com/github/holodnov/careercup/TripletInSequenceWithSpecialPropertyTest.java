package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.TripletInSequenceWithSpecialProperty.findTriplet;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=5683135085805568">http://www.careercup.com/question?id=5683135085805568</a>
 * 
 * @author Kyrylo Holodnov
 */
public class TripletInSequenceWithSpecialPropertyTest {

    @Test(expected = IllegalArgumentException.class)
    public void testFindTripletWithNullArray() {
	findTriplet(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTripletWithEmptyArray() {
	findTriplet(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTripletWithTwoElementArray() {
	findTriplet(new int[] { 1, 2 });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTripletWithThreeElementsNonSpecialArray() {
	findTriplet(new int[] { 1, 2, 3 });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTripletWithFourElementsNonSpecialArray() {
	findTriplet(new int[] { 1, 2, 3, 4 });
    }

    @Test
    public void testFindTripletWithThreeElementArray() {
	int[] array = new int[] { 2, 2, 3 };
	int[] expected = new int[] { 2, 2, 3 };
	int[] res = findTriplet(array);
	assertThat(
		"Result is not matched with array " + Arrays.toString(array),
		res, is(expected));
    }

    @Test
    public void testFindTripletWithFourElementArray() {
	int[] array = new int[] { 2, 2, 1, 3 };
	int[] expected = new int[] { 2, 1, 3 };
	int[] res = findTriplet(array);
	assertThat(
		"Result is not matched with array " + Arrays.toString(array),
		res, is(expected));
    }

    @Test
    public void testFindTripletWithFiveElementArray() {
	int[] array = new int[] { 2, 2, 1, 3, 5 };
	int[] expected = new int[] { 2, 1, 3 };
	int[] res = findTriplet(array);
	assertThat(
		"Result is not matched with array " + Arrays.toString(array),
		res, is(expected));
    }

    @Test
    public void testFindTripletWithLongArray() {
	int[] array = new int[] { 9, 8, 5, 4, 3, 2, 6, 7 };
	int[] expected = new int[] { 3, 2, 6 };
	int[] res = findTriplet(array);
	assertThat(
		"Result is not matched with array " + Arrays.toString(array),
		res, is(expected));
    }

}
