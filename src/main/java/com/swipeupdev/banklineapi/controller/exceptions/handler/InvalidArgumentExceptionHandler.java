package com.swipeupdev.banklineapi.controller.exceptions.handler;

import com.swipeupdev.banklineapi.controller.exceptions.StandardError;
import com.swipeupdev.banklineapi.model.exception.InvalidArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class InvalidArgumentExceptionHandler extends HandleException<InvalidArgumentException> {

    @Override
    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<StandardError> handler(InvalidArgumentException e, HttpServletRequest request) {
        return this.handler(e, request, "Argumento inválido.", HttpStatus.BAD_REQUEST);
    }

}
