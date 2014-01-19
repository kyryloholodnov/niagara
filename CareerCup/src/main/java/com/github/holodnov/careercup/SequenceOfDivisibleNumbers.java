package com.github.holodnov.careercup;

import java.util.HashSet;
import java.util.Set;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=20810664">http://www.careercup.com/question?id=20810664</a>
 * 
 * @author Kyrylo Holodnov
 */
public class SequenceOfDivisibleNumbers {

    public static int getNthNumber(int n) {
	if (n <= 0) {
	    throw new IllegalArgumentException(
		    "Input parameter should be positive");
	}
	if (n <= 5) {
	    return n;
	}
	Set<Integer> numbers = new HashSet<Integer>();
	numbers.add(1);
	numbers.add(2);
	numbers.add(3);
	numbers.add(4);
	numbers.add(5);
	int i = 5;
	int pos = 5;
	while (pos < n) {
	    i++;
	    if (((i % 2) == 0 && numbers.contains(i / 2))
		    || ((i % 3) == 0 && numbers.contains(i / 3))
		    || ((i % 5) == 0 && numbers.contains(i / 5))) {
		numbers.add(i);
		pos++;
	    }
	}
	return i;
    }

}
