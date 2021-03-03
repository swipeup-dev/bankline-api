package com.swipeupdev.banklineapi.controller.exceptions.handler;

import com.swipeupdev.banklineapi.controller.exceptions.StandardError;
import com.swipeupdev.banklineapi.model.exception.ExistingRecordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExistingRecordExceptionHandler extends HandleException<ExistingRecordException>{
    @Override
    @ExceptionHandler(ExistingRecordException.class)
    public ResponseEntity<StandardError> handler(ExistingRecordException e, HttpServletRequest request) {
        return super.handler(e, request, "Registro existente.", HttpStatus.BAD_REQUEST);
    }
}
