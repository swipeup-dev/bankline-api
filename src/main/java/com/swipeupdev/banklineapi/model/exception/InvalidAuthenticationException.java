package com.swipeupdev.banklineapi.model.exception;

public class InvalidAuthenticationException extends RuntimeException {
    private static final long serialVersionUID = -7947684696998650655L;

    public InvalidAuthenticationException(String message) {
        super(message);
    }
}
