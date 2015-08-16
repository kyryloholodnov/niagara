package com.github.holodnov.careercup;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=20235671">http://www.careercup.com/question?id=20235671</a>
 */
public class LongestRunningSequenceOfCharacters {

    public static Entry<Character, Integer> getLongestRunningSequence(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        Map<Character, Integer> initial = new HashMap<>();
        List<StringStat> statistic = new ArrayList<>();
        for (String str : strs) {
            if (str == null || str.isEmpty()) {
                continue;
            }
            StringStat stat = new StringStat(str);
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
        if (statistic.isEmpty() && initial.isEmpty()) {
            return null;
        } else if (statistic.isEmpty()) {
            char c = '\0';
            int length = 0;
            for (Entry<Character, Integer> entry : initial.entrySet()) {
                if (entry.getValue() > length) {
                    c = entry.getKey();
                    length = entry.getValue();
                }
            }
            return new SimpleImmutableEntry<>(c, length);
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
                if (i == j || statistic.get(i).suffix != statistic.get(j).prefix) {
                    continue;
                }
                Integer init = initial.get(statistic.get(i).suffix);
                if (init == null) {
                    init = 0;
                }
                int candidate = init + statistic.get(i).suffixLength + statistic.get(j).prefixLength;
                if (candidate > length) {
                    length = candidate;
                    c = statistic.get(i).suffix;
                }
            }
        }
        return new SimpleImmutableEntry<>(c, length);
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
            while (i < s.length() && prefix == s.charAt(i)) {
                i++;
            }
            prefixLength = i;
            suffix = s.charAt(s.length() - 1);
            i = s.length() - 2;
            while (i >= 0 && s.charAt(i) == suffix) {
                i--;
            }
            suffixLength = s.length() - 1 - i;
            sameSymbolsInString = prefixLength == s.length();
        }
    }
}
