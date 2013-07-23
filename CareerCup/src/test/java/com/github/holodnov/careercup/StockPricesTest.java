package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.StockPrices.findBenefit;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=22491681">http://www.careercup.com/question?id=22491681</a>
 * 
 * @author Kyrylo Holodnov
 */
public class StockPricesTest {
    @Test(expected = IllegalArgumentException.class)
    public void testFindBenefitNullArray() {
	findBenefit(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindBenefitZeroArray() {
	findBenefit(new int[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindBenefitOneElementArray() {
	findBenefit(new int[] { 1 });
    }

    @Test
    public void testFindBenefit1() {
	int res = findBenefit(new int[] { 5, 1, 4, 6, 7, 8, 4, 3, 7, 9 });
	assertThat(res, is(8));
    }

    @Test
    public void testFindBenefit2() {
	int res = findBenefit(new int[] { 2, 100, 1, 2 });
	assertThat(res, is(98));
    }

    @Test
    public void testFindBenefit3() {
	int res = findBenefit(new int[] { 1, 2, 2, 100 });
	assertThat(res, is(99));
    }

    @Test
    public void testFindBenefit4() {
	int res = findBenefit(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
	assertThat(res, is(9));
    }

    @Test
    public void testFindBenefit5() {
	int res = findBenefit(new int[] { 3, 2, 1 });
	assertThat(res, is(-1));
    }
}
