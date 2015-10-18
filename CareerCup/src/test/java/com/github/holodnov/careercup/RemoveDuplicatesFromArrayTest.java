package com.github.holodnov.careercup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static com.github.holodnov.careercup.RemoveDuplicatesFromArray.removeDuplicates;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=22360665">http://www.careercup.com/question?id=22360665</a>
 */
@RunWith(Parameterized.class)
public class RemoveDuplicatesFromArrayTest {

    private final int[] actual;
    private final int[] expected;

    public RemoveDuplicatesFromArrayTest(int[] actual, int[] expected) {
        this.actual = actual;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return asList(
                new Object[]{null, null},
                new Object[]{new int[0], new int[0]},
                new Object[]{new int[]{5}, new int[]{5}},
                new Object[]{new int[]{1, 2, 3, 2, 1}, new int[]{1, 2, 3}},
                new Object[]{new int[]{3, 2, 4, 3, 5, 4}, new int[]{3, 2, 4, 5}},
                new Object[]{new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}},
                new Object[]{new int[]{1, 2, 3, 4, 5, 6, 7, 8, 8, 9, 10}, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}},
                new Object[]{new int[]{5, 5, 5, 5, 5}, new int[]{5}},
                new Object[]{new int[]{5, 5, 5, 5, 5, 1, 1}, new int[]{5, 1}}
        );
    }

    @Test
    public void testRemoveDuplicates() {
        assertThat(removeDuplicates(actual), is(expected));
    }
}
