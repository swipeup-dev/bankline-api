package com.swipeupdev.banklineapi.controller.exceptions.handler;

import com.swipeupdev.banklineapi.controller.exceptions.StandardError;
import com.swipeupdev.banklineapi.model.exception.EntityRequirementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class EntityRequirementExceptionHandler extends HandleException<EntityRequirementException> {
    @Override
    @ExceptionHandler(EntityRequirementException.class)
    public ResponseEntity<StandardError> handler(EntityRequirementException e, HttpServletRequest request) {
        return this.handler(e, request, "Requisito inválido.", HttpStatus.BAD_REQUEST);
    }
}
