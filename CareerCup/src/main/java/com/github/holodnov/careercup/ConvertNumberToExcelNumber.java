package com.github.holodnov.careercup;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=24256664">http://www.careercup.com/question?id=24256664</a>
 */
public class ConvertNumberToExcelNumber {

    public static String convert(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Argument is not positive");
        }
        StringBuilder builder = new StringBuilder();
        while (n > 0) {
            n--;
            int remainder = n % 26;
            n /= 26;
            builder.append((char) (remainder + 65));
        }
        return builder.reverse().toString();
    }
}
