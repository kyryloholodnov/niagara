package com.github.holodnov.graph.exception;

/**
 * @author Kyrylo Holodnov
 */
public class UnparseableGraphDataException extends Exception {

    private static final long serialVersionUID = 201503291511L;

    public UnparseableGraphDataException() {
        super();
    }

    public UnparseableGraphDataException(String message) {
        super(message);
    }

    public UnparseableGraphDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnparseableGraphDataException(Throwable cause) {
        super(cause);
    }
}
