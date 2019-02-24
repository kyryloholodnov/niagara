package com.github.holodnov.algorithms.math;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static com.github.holodnov.algorithms.math.NumberUtils.findFactoring;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class NumberUtilsTest {

    private final long value;
    private final List<long[]> expected;

    public NumberUtilsTest(long value, List<long[]> expected) {
        this.value = value;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return asList(
                new Object[]{1, emptyList()},
                new Object[]{2, singletonList(new long[]{2, 1})},
                new Object[]{3, singletonList(new long[]{3, 1})},
                new Object[]{4, singletonList(new long[]{2, 2})},
                new Object[]{10, asList(new long[]{2, 1}, new long[]{5, 1})},
                new Object[]{32416190039L, singletonList(new long[]{32416190039L, 1})},
                new Object[]{20 * 32416190039L, asList(
                        new long[]{2, 2}, new long[]{5, 1}, new long[]{32416190039L, 1}
                )}
        );
    }

    @Test
    public void testFindFactoring() {
        List<long[]> factoring = findFactoring(value);
        assertEquals(expected.size(), factoring.size());
        for (int i = 0; i < factoring.size(); i++) {
            assertArrayEquals(expected.get(i), factoring.get(i));
        }
    }
}
