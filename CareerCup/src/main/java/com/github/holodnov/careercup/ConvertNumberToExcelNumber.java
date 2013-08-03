package com.github.holodnov.careercup;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=24256664">http://www.careercup.com/question?id=24256664</a>
 * 
 * @author Kyrylo Holodnov
 */
public class ConvertNumberToExcelNumber {
    public static String convert(int n) {
	if (n <= 0) {
	    throw new IllegalArgumentException("Argument is not positive");
	}
	StringBuilder res = new StringBuilder();
	while (true) {
	    n--;
	    int remainder = n % 26;
	    n /= 26;
	    res.append((char) (remainder + 65));
	    if (n == 0) {
		break;
	    }
	}
	return res.reverse().toString();
    }
}
