package com.swipeupdev.banklineapi.controller.exceptions.handler;

import com.swipeupdev.banklineapi.controller.exceptions.StandardError;
import com.swipeupdev.banklineapi.model.exception.InvalidAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class InvalidAuthenticationExceptionHandler extends HandleException<InvalidAuthenticationException>{
    @Override
    @ExceptionHandler(InvalidAuthenticationException.class)
    public ResponseEntity<StandardError> handler(InvalidAuthenticationException e, HttpServletRequest request) {
        return this.handler(e, request, "Autenticação inválida.", HttpStatus.UNAUTHORIZED);
    }
}
