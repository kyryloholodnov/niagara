package com.github.holodnov.careercup;

import org.junit.Test;

import static com.github.holodnov.careercup.ConvertNumberToExcelNumber.convert;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=24256664">http://www.careercup.com/question?id=24256664</a>
 */
public class ConvertNumberToExcelNumberTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConvertNegativeN() {
        convert(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertZeroN() {
        convert(0);
    }

    @Test
    public void testConvert() {
        assertThat(convert(1), is("A"));
        assertThat(convert(26), is("Z"));
        assertThat(convert(27), is("AA"));
        assertThat(convert(52), is("AZ"));
        assertThat(convert(53), is("BA"));
        assertThat(convert(54), is("BB"));
        assertThat(convert(676), is("YZ"));
        assertThat(convert(702), is("ZZ"));
        assertThat(convert(703), is("AAA"));
    }
}
