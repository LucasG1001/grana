package com.lucas.grana.domain.exceptions.token;

import com.lucas.grana.domain.exceptions.ExpirationTimeException;

public class ExpiredAuthTokenException extends ExpirationTimeException {

    public ExpiredAuthTokenException() {
        super("Token expirado");
    }

}
