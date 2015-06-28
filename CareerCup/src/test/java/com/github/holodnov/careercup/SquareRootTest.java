package com.github.holodnov.careercup;

import org.junit.Test;

import java.math.BigDecimal;

import static com.github.holodnov.careercup.SquareRoot.getSquareRoot;
import static org.junit.Assert.assertTrue;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=6657802751705088">http://www.careercup.com/question?id=6657802751705088</a>
 */
public class SquareRootTest {

    private static final BigDecimal EPS = BigDecimal.valueOf(0.000001);

    @Test(expected = IllegalArgumentException.class)
    public void testGetSquareRootNullArgument() {
        getSquareRoot(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSquareRootNegativeArgument() {
        getSquareRoot(BigDecimal.valueOf(-1));
    }

    @Test
    public void testGetSquareRoot1() {
        BigDecimal expected = BigDecimal.ZERO;
        BigDecimal actual = getSquareRoot(BigDecimal.valueOf(0));
        BigDecimal diff = expected.subtract(actual).abs();
        assertTrue("Expected: " + expected.toString() + ", actual: " + actual.toString(), EPS.compareTo(diff) >= 0);
    }

    @Test
    public void testGetSquareRoot2() {
        BigDecimal expected = BigDecimal.ONE;
        BigDecimal actual = getSquareRoot(BigDecimal.valueOf(1));
        BigDecimal diff = expected.subtract(actual).abs();
        assertTrue("Expected: " + expected.toString() + ", actual: " + actual.toString(), EPS.compareTo(diff) > 0);
    }

    @Test
    public void testGetSquareRoot3() {
        BigDecimal expected = BigDecimal.valueOf(2);
        BigDecimal actual = getSquareRoot(BigDecimal.valueOf(4));
        BigDecimal diff = expected.subtract(actual).abs();
        assertTrue("Expected: " + expected.toString() + ", actual: " + actual.toString(), EPS.compareTo(diff) >= 0);
    }

    @Test
    public void testGetSquareRoot4() {
        BigDecimal expected = BigDecimal.valueOf(2.2360679);
        BigDecimal actual = getSquareRoot(BigDecimal.valueOf(5));
        BigDecimal diff = expected.subtract(actual).abs();
        assertTrue("Expected: " + expected.toString() + ", actual: " + actual.toString(), EPS.compareTo(diff) >= 0);
    }

    @Test
    public void testGetSquareRoot5() {
        BigDecimal expected = BigDecimal.valueOf(15.9687194);
        BigDecimal actual = getSquareRoot(BigDecimal.valueOf(255));
        BigDecimal diff = expected.subtract(actual).abs();
        assertTrue("Expected: " + expected.toString() + ", actual: " + actual.toString(), EPS.compareTo(diff) >= 0);
    }

    @Test
    public void testGetSquareRoot6() {
        BigDecimal expected = BigDecimal.valueOf(11211545.2055602);
        BigDecimal actual = getSquareRoot(BigDecimal.valueOf(125698745896322L));
        BigDecimal diff = expected.subtract(actual).abs();
        assertTrue("Expected: " + expected.toString() + ", actual: " + actual.toString(), EPS.compareTo(diff) >= 0);
    }

    @Test
    public void testGetSquareRoot7() {
        BigDecimal expected = new BigDecimal("26833975921345506515565100942040265" +
                "4798698478374485342220347167067606359331437842378251265511205775" +
                "19886450945997704951989711275855443124989613290846717744.0325079");
        BigDecimal actual = getSquareRoot(new BigDecimal("72006226374735042527956443552558373833808445147399984182665305798191"
                + "63556901883377904234086641876639384851752649940178970835240791356868"
                + "77441155132015188279331812309091996246361896836573643119174094961348"
                + "52463970788523879939683923036467667022162701835329944324119217381272"
                + "9276147530748597302192751375739387929"));
        BigDecimal diff = expected.subtract(actual).abs();
        assertTrue("Expected: " + expected.toString() + ", actual: " + actual.toString(), EPS.compareTo(diff) >= 0);
    }
}
