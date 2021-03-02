package com.swipeupdev.banklineapi.model.exception;

public class InvalidArgumentException extends RuntimeException {

    private static final long serialVersionUID = -8189666841231948614L;
    public InvalidArgumentException(String message) {
        super(message);
    }
}
