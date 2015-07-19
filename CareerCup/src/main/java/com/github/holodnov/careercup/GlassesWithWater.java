package com.github.holodnov.careercup;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=22191662">http://www.careercup.com/question?id=22191662</a>
 */
public class GlassesWithWater {

    private static final double MAX_AMOUNT_OF_WATER_IN_GLASS = 1.0d;

    public static double getAmountOfWater(int level, int column, double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("Amount of water should be positive");
        }
        if (level < 0) {
            throw new IllegalArgumentException("Level should be non-negative");
        }
        if (column < 0) {
            throw new IllegalArgumentException("Column should be non-negative");
        }
        if (level < column) {
            throw new IllegalArgumentException("Level should be greater or equal to column");
        }
        if (level == 0) {
            return min(MAX_AMOUNT_OF_WATER_IN_GLASS, x);
        } else if (level == 1) {
            return max((x - MAX_AMOUNT_OF_WATER_IN_GLASS) / 2, 0);
        }
        double[] prevLevelVolumes = new double[]{
                max((x - MAX_AMOUNT_OF_WATER_IN_GLASS) / 2, 0),
                max((x - MAX_AMOUNT_OF_WATER_IN_GLASS) / 2, 0)};
        int i = 1;
        while (true) {
            i++;
            double[] levelVolumes = new double[i + 1];
            levelVolumes[0] = max((prevLevelVolumes[0] - 1) / 2, 0);
            levelVolumes[i] = max((prevLevelVolumes[i - 1] - 1) / 2, 0);
            for (int j = 1; j < levelVolumes.length - 1; j++) {
                levelVolumes[j] = max((prevLevelVolumes[j - 1] - MAX_AMOUNT_OF_WATER_IN_GLASS) / 2, 0)
                        + max((prevLevelVolumes[j] - MAX_AMOUNT_OF_WATER_IN_GLASS) / 2, 0);
            }
            if (i == level) {
                return levelVolumes[column];
            }
            prevLevelVolumes = levelVolumes;
        }
    }
}
