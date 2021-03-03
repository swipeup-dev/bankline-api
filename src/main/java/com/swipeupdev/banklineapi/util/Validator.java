package com.swipeupdev.banklineapi.util;

import com.swipeupdev.banklineapi.model.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Component
public class Validator {

    @Autowired
    private javax.validation.Validator validator;

    public <T> void validate(T var, Class<?>... var2) {
        Set<ConstraintViolation<T>> violations = validator.validate(var, var2);
        for (ConstraintViolation<T> violation : violations) {
            throw new InvalidArgumentException(violation.getMessage());
        }
    }

}
