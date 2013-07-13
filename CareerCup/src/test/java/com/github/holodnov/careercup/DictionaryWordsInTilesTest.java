package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.DictionaryWordsInTiles.findDictionaryWords;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=5711103912837120">http://www.careercup.com/question?id=5711103912837120</a>
 * 
 * @author Kyrylo Holodnov
 */
@RunWith(Parameterized.class)
public class DictionaryWordsInTilesTest {

    private String tiles;
    private String[] dictionary;
    private String[] expected;

    public DictionaryWordsInTilesTest(String tiles, String[] dictionary,
	    String[] expected) {
	this.tiles = tiles;
	this.dictionary = dictionary;
	this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
	return Arrays
		.asList(new Object[] { null, null, new String[0] },
			new Object[] { null, new String[0], new String[0] },
			new Object[] { "", null, new String[0] },
			new Object[] { "", new String[0], new String[0] },
			new Object[] { "ABC", new String[0], new String[0] },
			new Object[] { "ABC",
				new String[] { "A", "AA", "AAA" },
				new String[] { "A" } },
			new Object[] { "ABCDEFG",
				new String[] { "ABCDEFG", "BCDEFG" },
				new String[] { "ABCDEFG", "BCDEFG" } },
			new Object[] {
				"ABCDEFG",
				new String[] { "A", "B", "AB", "ABC",
					"ABCDEFG", "AABCDEFG" },
				new String[] { "A", "AB", "ABC", "ABCDEFG", "B" } },
			new Object[] { "AABC",
				new String[] { "A", "AA", "AAA" },
				new String[] { "A", "AA" } }, new Object[] {
				"AAA", new String[] { "A", "AA", "AAA" },
				new String[] { "A", "AA", "AAA" } },
			new Object[] {
				"SAPAPER",
				new String[] { "A", "AA", "AAA", "APE", "PEA",
					"PARE", "PEAR", "FEAR", "SPARE",
					"APPEARS", "REAPPEARS" },
				new String[] { "A", "AA", "APE", "APPEARS",
					"PARE", "PEA", "PEAR", "SPARE" } });
    }

    @Test
    public void testFindDictionaryWords() {
	String[] result = findDictionaryWords(tiles, dictionary);
	Arrays.sort(result);
	assertThat("Result does not match for tiles = " + tiles
		+ " and dictionary = " + Arrays.toString(dictionary), result,
		is(expected));
    }

}
