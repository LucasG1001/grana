package com.lucas.grana.domain.exceptions.token;

import java.security.InvalidParameterException;

public class InvalidAuthTokenException extends InvalidParameterException {

    public InvalidAuthTokenException() {
        super("Token inválido");
    }
}