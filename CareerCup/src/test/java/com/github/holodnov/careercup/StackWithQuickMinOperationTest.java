package com.github.holodnov.careercup;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=5737979670691840">http://www.careercup.com/question?id=5737979670691840</a>
 * 
 * @author Kyrylo Holodnov
 */
public class StackWithQuickMinOperationTest {

    @Test(expected = IllegalArgumentException.class)
    public void testPushNullValue() {
	StackWithQuickMinOperation stack = new StackWithQuickMinOperation();
	assertThat(stack.size(), is(0));
	stack.push(null);
    }

    @Test
    public void testPushAndSize() {
	StackWithQuickMinOperation stack = new StackWithQuickMinOperation();
	assertThat(stack.size(), is(0));
	stack.push(10);
	stack.push(11);
	stack.push(12);
	assertThat(stack.size(), is(3));
    }

    @Test
    public void testPushAndPop() {
	StackWithQuickMinOperation stack = new StackWithQuickMinOperation();
	assertThat(stack.size(), is(0));
	stack.push(10);
	stack.push(11);
	stack.push(12);
	assertThat(stack.size(), is(3));
	assertThat(stack.pop(), is(12));
	assertThat(stack.size(), is(2));
	assertThat(stack.pop(), is(11));
	assertThat(stack.size(), is(1));
	assertThat(stack.pop(), is(10));
	assertThat(stack.size(), is(0));
	assertNull(stack.pop());
	assertNull(stack.peek());
    }

    @Test
    public void testPeekMin() {
	StackWithQuickMinOperation stack = new StackWithQuickMinOperation();
	stack.push(10);
	assertThat(stack.peekMin(), is(10));
	stack.push(11);
	assertThat(stack.peekMin(), is(10));
	stack.push(12);
	assertThat(stack.peekMin(), is(10));
	stack.push(9);
	assertThat(stack.peekMin(), is(9));
	stack.push(8);
	assertThat(stack.peekMin(), is(8));
	stack.push(15);
	assertThat(stack.peekMin(), is(8));
	stack.push(1);
	stack.push(2);
	assertThat(stack.peekMin(), is(1));
    }
}
