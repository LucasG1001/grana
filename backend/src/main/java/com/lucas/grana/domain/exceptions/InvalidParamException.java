package com.lucas.grana.domain.exceptions;

public class InvalidParamException extends RuntimeException {
    public InvalidParamException(String message) {
        super(message);
    }
}
