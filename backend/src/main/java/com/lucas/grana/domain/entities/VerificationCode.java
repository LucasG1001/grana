package com.lucas.grana.domain.entities;

import java.time.LocalDateTime;

import com.lucas.grana.domain.valueObjects.security.Code;

public class VerificationCode {

    private final Code code;
    private LocalDateTime expiresAt;

    public VerificationCode(Code code, LocalDateTime expiresAt) {
        this.code = code;
        this.expiresAt = expiresAt;
    }

    public Code getCode() {
        return code;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
}
