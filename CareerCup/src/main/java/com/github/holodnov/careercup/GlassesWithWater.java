package com.github.holodnov.careercup;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=22191662">http://www.careercup.com/question?id=22191662</a>
 * 
 * @author Kyrylo Holodnov
 */
public class GlassesWithWater {

    private static final double MAX_AMOUNT_OF_WATER_IN_GLASS = 1.0d;

    public static double getAmountOfWater(int level, int column, double x) {
	if (x <= 0) {
	    throw new IllegalArgumentException(
		    "Amount of water should be positive");
	}
	if (level < 0) {
	    throw new IllegalArgumentException("Level should be non-negative");
	}
	if (column < 0) {
	    throw new IllegalArgumentException("Column should be non-negative");
	}
	if (level < column) {
	    throw new IllegalArgumentException(
		    "Level should be greater or equal to column");
	}
	if (level == 0) {
	    return Math.min(MAX_AMOUNT_OF_WATER_IN_GLASS, x);
	} else if (level == 1) {
	    return Math.max((x - MAX_AMOUNT_OF_WATER_IN_GLASS) / 2, 0);
	}
	double[] prevLevel = new double[] {
		Math.max((x - MAX_AMOUNT_OF_WATER_IN_GLASS) / 2, 0),
		Math.max((x - MAX_AMOUNT_OF_WATER_IN_GLASS) / 2, 0) };
	int i = 1;
	while (true) {
	    i++;
	    double[] volumes = new double[i + 1];
	    volumes[0] = Math.max((prevLevel[0] - 1) / 2, 0);
	    volumes[i] = Math.max((prevLevel[i - 1] - 1) / 2, 0);
	    for (int j = 1; j < volumes.length - 1; j++) {
		volumes[j] = Math.max(
			(prevLevel[j - 1] - MAX_AMOUNT_OF_WATER_IN_GLASS) / 2,
			0)
			+ Math.max(
				(prevLevel[j] - MAX_AMOUNT_OF_WATER_IN_GLASS) / 2,
				0);
	    }
	    if (i == level) {
		return volumes[column];
	    }
	    prevLevel = volumes;
	}
    }
}
