package com.swipeupdev.banklineapi.controller.exceptions.handler;

import com.swipeupdev.banklineapi.controller.exceptions.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class PersistenceExceptionHandler extends HandleException<PersistenceException> {
    @Override
    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<StandardError> handler(PersistenceException e, HttpServletRequest request) {
        return this.handler(e, request, "Ocorreu uma falha ao persistir os dados.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
