package com.lucas.grana.domain.entities;

import java.time.LocalDateTime;
import com.lucas.grana.domain.valueObjects.security.Token;

public class AuthToken {

    private final Token token;
    private LocalDateTime expiresAt;

    public AuthToken(Token token, LocalDateTime expiresAt) {
        this.token = token;
        this.expiresAt = expiresAt;
    }

    public Token gettoken() {
        return token;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
}
