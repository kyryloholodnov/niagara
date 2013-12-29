package com.github.holodnov.careercup;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=5910450793349120">http://www.careercup.com/question?id=5910450793349120</a>
 * 
 * @author Kyrylo Holodnov
 */
public class ValidNumber {

    public static boolean isValid(String number) {
	if (number == null || number.isEmpty()) {
	    throw new IllegalArgumentException(
		    "Input string should be not null and not empty");
	}
	if (number.length() == 1 && number.charAt(0) - '0' >= 0
		&& number.charAt(0) - '0' <= 9) {
	    return true;
	}
	int illegalSymbol = -1;
	boolean invalid = false;
	boolean[] digits = new boolean[10];
	for (int i = 0; i < number.length(); i++) {
	    int digit = number.charAt(i) - '0';
	    if (digit < 0 || digit > 9) {
		illegalSymbol = i;
		break;
	    }
	    invalid |= digit == 0 || digit == 1 || digits[digit];
	    digits[digit] = true;
	}
	if (illegalSymbol > -1) {
	    throw new IllegalArgumentException("Digit #" + illegalSymbol
		    + " is not valid");
	}
	if (invalid) {
	    return false;
	}
	return !(digits[2] && digits[3] && digits[6])
		&& !(digits[2] && digits[4] && digits[8])
		&& !(digits[3] && digits[4] && digits[6] && digits[8]);
    }

}
