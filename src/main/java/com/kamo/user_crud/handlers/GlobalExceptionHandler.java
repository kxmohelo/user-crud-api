package com.kamo.user_crud.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * GlobalExceptionHandler is a centralized exception handling component for the application.
 * It uses @ControllerAdvice to apply global exception handling across all controllers.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles all exceptions that occur during the execution of controller methods.
     *
     * @param ex The exception that was thrown
     * @param request The current web request during which the exception occurred
     * @return A ResponseEntity containing the exception message and HTTP status 500 (Internal Server Error)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
