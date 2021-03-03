package com.swipeupdev.banklineapi.controller.exceptions.handler;

import com.swipeupdev.banklineapi.controller.exceptions.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class HttpMessageNotReadableExceptionHandler extends HandleException<HttpMessageNotReadableException>{
    @Override
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> handler(HttpMessageNotReadableException e, HttpServletRequest request) {
        return this.handler(e, request, "JSON inválido", HttpStatus.BAD_REQUEST);
    }
}
