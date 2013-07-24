package com.github.holodnov.careercup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=20235671">http://www.careercup.com/question?id=20235671</a>
 * 
 * @author Kyrylo Holodnov
 */
public class LongestRunningSequenceOfCharacters {
    public static CharAndInteger getLongestRunningSequence(String[] strs) {
	Map<Character, Integer> initial = new HashMap<Character, Integer>();
	List<StringStat> statistic = new ArrayList<StringStat>();
	for (int i = 0; i < strs.length; i++) {
	    if ((strs[i] == null) || (strs[i].isEmpty())) {
		continue;
	    }
	    StringStat stat = new StringStat(strs[i]);
	    if (stat.sameSymbolsInString) {
		Integer initSize = initial.get(stat.prefix);
		if (initSize == null) {
		    initSize = stat.prefixLength;
		} else {
		    initSize += stat.prefixLength;
		}
		initial.put(stat.prefix, initSize);
	    } else {
		statistic.add(stat);
	    }
	}
	if ((statistic.isEmpty() && (initial.isEmpty()))) {
	    return null;
	} else if (statistic.isEmpty()) {
	    char c = '\0';
	    int length = 0;
	    for (Map.Entry<Character, Integer> entry : initial.entrySet()) {
		if (entry.getValue() > length) {
		    c = entry.getKey();
		    length = entry.getValue();
		}
	    }
	    return new CharAndInteger(c, length);
	}
	char c = '\0';
	int length = 0;
	for (StringStat stat : statistic) {
	    Integer init = initial.get(stat.prefix);
	    if (init == null) {
		init = 0;
	    }
	    if (stat.prefixLength + init > length) {
		length = stat.prefixLength + init;
		c = stat.prefix;
	    }
	    init = initial.get(stat.suffix);
	    if (init == null) {
		init = 0;
	    }
	    if (stat.suffixLength + init > length) {
		length = stat.suffixLength + init;
		c = stat.suffix;
	    }
	}
	for (int i = 0; i < statistic.size(); i++) {
	    for (int j = 0; j < statistic.size(); j++) {
		if ((i == j)
			|| (statistic.get(i).suffix != statistic.get(j).prefix)) {
		    continue;
		}
		Integer init = initial.get(statistic.get(i).suffix);
		if (init == null) {
		    init = 0;
		}
		int candidate = init + statistic.get(i).suffixLength
			+ statistic.get(j).prefixLength;
		if (candidate > length) {
		    length = candidate;
		    c = statistic.get(i).suffix;
		}
	    }
	}
	return new CharAndInteger(c, length);
    }

    private static final class StringStat {
	public final char prefix;
	public final char suffix;
	public final int prefixLength;
	public final int suffixLength;
	public final boolean sameSymbolsInString;

	public StringStat(String s) {
	    prefix = s.charAt(0);
	    int i = 1;
	    while ((i < s.length()) && (prefix == s.charAt(i))) {
		i++;
	    }
	    prefixLength = i;
	    suffix = s.charAt(s.length() - 1);
	    i = s.length() - 2;
	    while ((i >= 0) && (s.charAt(i) == suffix)) {
		i--;
	    }
	    suffixLength = s.length() - 1 - i;
	    sameSymbolsInString = prefixLength == s.length();
	}
    }

    public static class CharAndInteger {
	public final char c;
	public final int length;

	public CharAndInteger(char c, int length) {
	    this.c = c;
	    this.length = length;
	}

	public int hashCode() {
	    return 41 * c + 53 * length;
	}

	public boolean equals(Object o) {
	    if (o == this) {
		return true;
	    }
	    if ((o == null) || (o.getClass() != CharAndInteger.class)) {
		return false;
	    }
	    CharAndInteger candidate = (CharAndInteger) o;
	    return ((c == candidate.c) && (length == candidate.length));
	}

	public String toString() {
	    return c + ": " + length;
	}
    }
}
