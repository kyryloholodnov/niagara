package com.github.holodnov.graph.zoo;

/**
 * @author Kyrylo Holodnov
 */
public class NoZooNodeException extends Exception {

    private static final long serialVersionUID = 7818375828146090123L;

    public NoZooNodeException() {
        super();
    }

    public NoZooNodeException(String message) {
        super(message);
    }

    public NoZooNodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoZooNodeException(Throwable cause) {
        super(cause);
    }
}
