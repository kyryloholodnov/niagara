package com.github.holodnov.careercup;

import java.util.LinkedList;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

/**
 * @author Kyrylo Holodnov
 * @see <a
 * href="http://www.careercup.com/question?id=5737979670691840">http://www.careercup.com/question?id=5737979670691840</a>
 */
public class StackWithQuickMinOperation {

    private final LinkedList<Integer> generalStack;
    private final LinkedList<Integer> minStack;

    public StackWithQuickMinOperation() {
        generalStack = new LinkedList<>();
        minStack = new LinkedList<>();
    }

    public void push(Integer value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot add null element to stack");
        }
        generalStack.addFirst(value);
        Integer lastMin = minStack.isEmpty() ? MAX_VALUE : minStack.getFirst();
        minStack.addFirst(min(value, lastMin));
    }

    public Integer pop() {
        if (generalStack.isEmpty()) {
            return null;
        }
        Integer element = generalStack.removeFirst();
        minStack.removeFirst();
        return element;
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
