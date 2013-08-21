package com.github.holodnov.careercup;

import java.util.LinkedList;

/**
 * @see <a
 *      href="http://www.careercup.com/question?id=5737979670691840">http://www.careercup.com/question?id=5737979670691840</a>
 * 
 * @author Kyrylo Holodnov
 */
public class StackWithQuickMinOperation {

    private final LinkedList<Integer> generalStack;
    private final LinkedList<Integer> minStack;

    public StackWithQuickMinOperation() {
	generalStack = new LinkedList<Integer>();
	minStack = new LinkedList<Integer>();
    }

    public void push(Integer value) {
	if (value == null) {
	    throw new IllegalArgumentException(
		    "Cannot add null element to stack");
	}
	generalStack.addFirst(value);
	Integer lastMin = minStack.isEmpty() ? Integer.MAX_VALUE : minStack
		.getFirst();
	minStack.addFirst(Math.min(value, lastMin));
    }

    public Integer pop() {
	if (generalStack.isEmpty()) {
	    return null;
	}
	Integer res = generalStack.removeFirst();
	minStack.removeFirst();
	return res;
    }

    public Integer peek() {
	return generalStack.peekFirst();
    }

    public Integer peekMin() {
	return minStack.peekFirst();
    }

    public int size() {
	return generalStack.size();
    }
}
