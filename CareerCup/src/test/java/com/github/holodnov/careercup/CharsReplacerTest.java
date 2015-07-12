package com.github.holodnov.careercup;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.github.holodnov.careercup.CharsReplacer.generatePasswords;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=5181985580384256">http://www.careercup.com/question?id=5181985580384256</a>
 */
public class CharsReplacerTest {

    private final Map<Character, char[]> replacer = new HashMap<>();

    {
        replacer.put('a', "12".toCharArray());
        replacer.put('b', "34".toCharArray());
        replacer.put('c', "56".toCharArray());
        replacer.put('d', "78".toCharArray());
        replacer.put('e', "90".toCharArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGeneratePasswordsNullWord() {
        generatePasswords(null, replacer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGeneratePasswordsNullReplacer() {
        generatePasswords("ab", null);
    }

    @Test
    public void testGeneratePasswords1() {
        Set<String> actual = generatePasswords("z", replacer);
        Set<String> expected = new HashSet<>(singletonList("z"));
        assertThat(actual, is(expected));
    }

    @Test
    public void testGeneratePasswords2() {
        Set<String> actual = generatePasswords("e", replacer);
        Set<String> expected = new HashSet<>(asList("e", "9", "0"));
        assertThat(actual, is(expected));
    }

    @Test
    public void testGeneratePasswords3() {
        Set<String> actual = generatePasswords("xey", replacer);
        Set<String> expected = new HashSet<>(asList("xey", "x9y", "x0y"));
        assertThat(actual, is(expected));
    }

    @Test
    public void testGeneratePasswords4() {
        Set<String> actual = generatePasswords("ab", replacer);
        Set<String> expected = new HashSet<>(asList("ab", "1b", "2b", "a3", "a4", "13", "14", "23", "24"));
        assertThat(actual, is(expected));
    }

    @Test
    public void testGeneratePasswords5() {
        Set<String> actual = generatePasswords("azb", replacer);
        Set<String> expected = new HashSet<>(asList("azb", "1zb", "2zb", "az3", "az4", "1z3", "1z4", "2z3", "2z4"));
        assertThat(actual, is(expected));
    }
}
