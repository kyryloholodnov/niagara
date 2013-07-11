package com.github.calculator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * 
 * @author Kyrylo Holodnov
 */

@RunWith(Parameterized.class)
public class ObjectSizeCalculatorTest {

	private Object object;
	private String tcName;
	private long result64bit;
	private long result32bit;
	private boolean machine64bit;

	public ObjectSizeCalculatorTest(Object object, String tcName,
			long result64bit, long result32bit) {
		this.object = object;
		this.tcName = tcName;
		this.result64bit = result64bit;
		this.result32bit = result32bit;
		this.machine64bit = System.getProperties().get("java.vm.name")
				.toString().contains("64");
	}

	@Parameterized.Parameters
	public static Collection<Object[]> instancesToTest() {
		return Arrays
				.asList(new Object[] { null, "null object", 0, 0 },
						new Object[] { new Object(), "new Object()", 16, 8 },
						new Object[] { "a", "String \"a\"", 56, 40 },
						new Object[] { "", "Empty String ", 56, 40 },
						new Object[] { new long[] { 1, 2, 3 },
								"new long[] {1, 2, 3}", 48, 40 },
						new Object[] { new int[] { 4, 5, 6 },
								"new int[] {4, 5, 6}", 32, 24 },
						new Object[] { new byte[] { 7, 8, 9 },
								"new byte[] {7, 8, 9}", 24, 16 },
						new Object[] { new boolean[] { true, false, true },
								"new boolean[] {true, false, true}", 24, 16 },
						new Object[] { new char[] { 'a', 'b', 'c' },
								"new char[] { 'a', 'b', 'c' }", 32, 24 },
						new Object[] { new short[] { 10, 11, 12 },
								"new short[] { 10, 11, 12 }", 32, 24 },
						new Object[] { new float[] { 1.0f, 2.0f, 3.0f },
								"new float[] { 1.0f, 2.0f, 3.0f }", 32, 24 },
						new Object[] { new double[] { 4.0, 5.0, 6.0 },
								"new double[] { 4.0, 5.0, 6.0 }", 48, 40 },
						new Object[] { Integer.valueOf(2013),
								"Integer.valueOf(2013)", 24, 16 },
						new Object[] { new Integer[] { Integer.valueOf(1) },
								"new Integer[] { Integer.valueOf(1) }", 56, 32 },
						new Object[] {
								new Integer[] { Integer.valueOf(1),
										Integer.valueOf(2), Integer.valueOf(3) },
								"new Integer[] { Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3) }",
								120, 72 },
						new Object[] {
								new Integer[][] {
										new Integer[] { Integer.valueOf(1) },
										new Integer[] { Integer.valueOf(2) },
										new Integer[] { Integer.valueOf(3) } },
								"new Integer[][] { new Integer[] { Integer.valueOf(1) }, new Integer[] { Integer.valueOf(2) }, new Integer[] { Integer.valueOf(3) } }",
								216, 120 });
	}

	@Test
	public void testSizeOf() throws IllegalAccessException {
		long size = ObjectSizeCalculator.sizeOf(object);
		assertThat("SizeOf for " + tcName + " does not match", size,
				is(machine64bit ? result64bit : result32bit));
	}
}
