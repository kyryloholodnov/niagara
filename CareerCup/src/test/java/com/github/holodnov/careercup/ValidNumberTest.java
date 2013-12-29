package com.github.holodnov.careercup;

import static com.github.holodnov.careercup.ValidNumber.isValid;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=5910450793349120">http://www.careercup.com/question?id=5910450793349120</a>
 * 
 * @author Kyrylo Holodnov
 */
public class ValidNumberTest {

    @Test(expected = IllegalArgumentException.class)
    public void testIsValidNullString() {
	isValid(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsValidEmptyNull() {
	isValid("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsValidStringIsNotANumber1() {
	isValid(" ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsValidStringIsNotANumber2() {
	isValid("2342 23");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsValidStringIsNotANumber3() {
	isValid("2342s23");
    }

    @Test
    public void testIsValidString() {
	assertTrue(isValid("0"));
	assertTrue(isValid("1"));
	assertFalse(isValid("12"));
	assertTrue(isValid("2"));
	assertFalse(isValid("1248"));
	assertFalse(isValid("248"));
	assertTrue(isValid("48"));
	assertFalse(isValid("77"));
	assertTrue(isValid("75468"));
	assertFalse(isValid("375468"));
	assertFalse(isValid("3625"));
	assertFalse(isValid("3525"));
	assertTrue(isValid("3425"));
    }

}
