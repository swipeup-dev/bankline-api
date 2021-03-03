package com.swipeupdev.banklineapi.controller.exceptions.handler;

import com.swipeupdev.banklineapi.controller.exceptions.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public abstract class HandleException<E extends Exception> {

    public abstract ResponseEntity<StandardError> handler(E e, HttpServletRequest request);

    protected ResponseEntity<StandardError> handler(
            E e, HttpServletRequest request, String message, HttpStatus status) {
        StandardError error = new StandardError(
                Instant.now(),
                status.value(),
                message,
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(error);
    }

}