package com.swipeupdev.banklineapi.model.exception;

public class RecordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -1934235367882618110L;

    public RecordNotFoundException(String message) {
        super(message);
    }
}
