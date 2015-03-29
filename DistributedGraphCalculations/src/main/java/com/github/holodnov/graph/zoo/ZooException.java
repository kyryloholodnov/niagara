package com.github.holodnov.graph.zoo;


public class ZooException extends Exception {

    private static final long serialVersionUID = 7818375828146090123L;

    public ZooException() {
        super();
    }

    public ZooException(String message) {
        super(message);
    }

    public ZooException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZooException(Throwable cause) {
        super(cause);
    }
}