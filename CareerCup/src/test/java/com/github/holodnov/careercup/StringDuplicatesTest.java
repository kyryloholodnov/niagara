package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.StringDuplicates.countDuplicatesWithHashSet;
import static com.github.holodnov.careercup.StringDuplicates.countDuplicatesWithTrie;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=22273665">http://www.careercup.com/question?id=22273665</a>
 * 
 * @author Kyrylo Holodnov
 */
public class StringDuplicatesTest {
    @Test(expected = IllegalArgumentException.class)
    public void testCountDuplicatesWithHashSetNullSentence() {
	countDuplicatesWithHashSet(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCountDuplicatesWithTrieNullSentence() {
	countDuplicatesWithTrie(null);
    }

    @Test
    public void testCountDuplicatesWithHashSet1() {
	String sentence = "";
	int res = countDuplicatesWithHashSet(sentence);
	assertThat(res, is(0));
    }

    @Test
    public void testCountDuplicatesWithTrie1() {
	String sentence = "";
	int res = countDuplicatesWithTrie(sentence);
	assertThat(res, is(0));
    }

    @Test
    public void testCountDuplicatesWithHashSet2() {
	String sentence = "I am a sentence";
	int res = countDuplicatesWithHashSet(sentence);
	assertThat(res, is(0));
    }

    @Test
    public void testCountDuplicatesWithTrie2() {
	String sentence = "I am a sentence";
	int res = countDuplicatesWithTrie(sentence);
	assertThat(res, is(0));
    }

    @Test
    public void testCountDuplicatesWithHashSet3() {
	String sentence = "I am a sentence I am a sentence";
	int res = countDuplicatesWithHashSet(sentence);
	assertThat(res, is(4));
    }

    @Test
    public void testCountDuplicatesWithTrie3() {
	String sentence = "I am a sentence I am a sentence";
	int res = countDuplicatesWithTrie(sentence);
	assertThat(res, is(4));
    }

    @Test
    public void testCountDuplicatesWithHashSet4() {
	String sentence = "sentence I am a sentence I am a sentence";
	int res = countDuplicatesWithHashSet(sentence);
	assertThat(res, is(5));
    }

    @Test
    public void testCountDuplicatesWithTrie4() {
	String sentence = "sentence I am a sentence I am a sentence";
	int res = countDuplicatesWithTrie(sentence);
	assertThat(res, is(5));
    }
}
