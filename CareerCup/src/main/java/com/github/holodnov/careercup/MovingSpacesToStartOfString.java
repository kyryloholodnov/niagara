package com.github.holodnov.careercup;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=5242643239927808">http://www.careercup.com/question?id=5242643239927808</a>
 */
public class MovingSpacesToStartOfString {

    public static void moveSpaces(char[] str) {
        if (str == null || str.length == 0) {
            return;
        }
        int i = str.length - 1;
        int j = str.length - 1;
        while (i >= 0) {
            if (str[i] == ' ') {
                i--;
                continue;
            }
            if (i < j) {
                char t = str[i];
                str[i] = str[j];
                str[j] = t;
            }
            i--;
            j--;
        }
        while (j >= 0) {
            str[j--] = ' ';
        }
    }
}
