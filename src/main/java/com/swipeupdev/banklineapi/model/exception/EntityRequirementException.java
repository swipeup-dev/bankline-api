package com.swipeupdev.banklineapi.model.exception;

public class EntityRequirementException extends RuntimeException {
    private static final long serialVersionUID = 5491793674767327337L;

    public EntityRequirementException(String message) {
        super(message);
    }
}
