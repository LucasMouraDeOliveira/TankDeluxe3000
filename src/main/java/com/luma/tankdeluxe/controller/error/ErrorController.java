package com.luma.tankdeluxe.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.luma.tankdeluxe.exception.user.UsernameAlreadyUsedException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(UsernameAlreadyUsedException.class)
    public ResponseEntity<ErrorResponse> handleException(UsernameAlreadyUsedException ex) {
        var errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), "username already taken");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleException(AuthenticationException ex) {
        var errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(), "invalid username or password");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }
}
