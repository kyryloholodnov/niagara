package com.github.holodnov.careercup;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=22491681">http://www.careercup.com/question?id=22491681</a>
 */
public class StockPrices {

    public static int findBenefit(int[] prices) {
        if (prices == null || prices.length < 2) {
            throw new IllegalArgumentException("Prices cannot be null or with length less than 2");
        }
        int[] min = new int[prices.length];
        int[] max = new int[prices.length];
        min[0] = prices[0];
        max[prices.length - 1] = prices[prices.length - 1];
        for (int i = 1; i < prices.length; i++) {
            min[i] = min(min[i - 1], prices[i]);
            max[prices.length - 1 - i] = max(max[prices.length - i], prices[prices.length - 1 - i]);
        }
        int benefit = MIN_VALUE;
        for (int i = 1; i < max.length; i++) {
            benefit = max(benefit, max[i] - min[i - 1]);
        }
        return benefit;
    }
}
