package com.lucas.grana.api.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lucas.grana.domain.exceptions.AlreadyExistsException;
import com.lucas.grana.domain.exceptions.ExpirationTimeException;
import com.lucas.grana.domain.exceptions.InvalidParamException;
import com.lucas.grana.domain.exceptions.NotGeneratedException;

@ControllerAdvice
public class ApiExceptionHandler {

    private ResponseEntity<Object> buildErrorResponse(RuntimeException ex, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("type", ex.getClass().getSimpleName().replace("Exception", ""));
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Object> handleAlreadyExistsException(AlreadyExistsException ex) {
        return buildErrorResponse(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExpirationTimeException.class)
    public ResponseEntity<Object> handleExpirationTimeException(ExpirationTimeException ex) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotGeneratedException.class)
    public ResponseEntity<Object> handleNotGeneratedException(NotGeneratedException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidParamException.class)
    public ResponseEntity<Object> handleInvalidParamException(InvalidParamException ex) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }
}
