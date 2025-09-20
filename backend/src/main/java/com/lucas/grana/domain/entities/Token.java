package com.lucas.grana.domain.entities;

import java.time.LocalDateTime;

import com.lucas.grana.domain.valueObjects.security.TokenValue;

public class Token {

    private final TokenValue value;
    private LocalDateTime expiresAt;

    public Token(TokenValue value, LocalDateTime expiresAt) {
        this.value = value;
        this.expiresAt = expiresAt;
    }

    public TokenValue getValue() {
        return value;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
}
