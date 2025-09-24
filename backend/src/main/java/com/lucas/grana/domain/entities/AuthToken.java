package com.lucas.grana.domain.entities;

import java.time.LocalDateTime;
import com.lucas.grana.domain.valueObjects.security.Token;
import lombok.Getter;

@Getter
public class AuthToken {

    private final Token token;
    private final LocalDateTime expiresAt;

    public AuthToken(Token token, LocalDateTime expiresAt) {
        this.token = token;
        this.expiresAt = expiresAt;
    }

}
