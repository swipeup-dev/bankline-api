package com.swipeupdev.banklineapi.controller.exceptions.handler;

import com.swipeupdev.banklineapi.controller.exceptions.StandardError;
import com.swipeupdev.banklineapi.model.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RecordNotFoundExceptionHandler extends HandleException<RecordNotFoundException> {
    @Override
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<StandardError> handler(RecordNotFoundException e, HttpServletRequest request) {
        return this.handler(e, request, "Registro não encontrado.", HttpStatus.BAD_REQUEST);
    }
}
