package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.SumOfTwoSquares.findNumberOfWays;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=5767787551129600">http://www.careercup.com/question?id=5767787551129600</a>
 * 
 * @author Kyrylo Holodnov
 */
@RunWith(Parameterized.class)
public class SumOfTwoSquaresTest {

    private int inputValue;
    private int expected;

    public SumOfTwoSquaresTest(int inputValue, int expected) {
	this.inputValue = inputValue;
	this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
	return Arrays.asList(new Object[] { -1, 0 }, new Object[] { 0, 1 },
		new Object[] { 1, 1 }, new Object[] { 2, 1 }, new Object[] { 3,
			0 }, new Object[] { 4, 1 }, new Object[] { 10, 1 });
    }

    @Test
    public void testFindNumberOfWays() {
	int actual = findNumberOfWays(inputValue);
	assertThat("Result for value = " + inputValue
		+ " does not match with expected = " + expected, actual,
		is(expected));
    }
}
