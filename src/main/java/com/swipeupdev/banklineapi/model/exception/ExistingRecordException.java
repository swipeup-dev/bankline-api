package com.swipeupdev.banklineapi.model.exception;

public class ExistingRecordException extends RuntimeException {
    private static final long serialVersionUID = -3739052678876365944L;

    public ExistingRecordException(String message) {
        super(message);
    }
}
