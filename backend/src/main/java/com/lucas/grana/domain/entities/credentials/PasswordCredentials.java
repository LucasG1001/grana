package com.lucas.grana.domain.entities.credentials;

import com.lucas.grana.domain.enums.user.AuthProvider;

public class PasswordCredentials extends Credentials {

    private final String passwordHash;
    private final String accessToken;
    private final String refreshToken;

    public PasswordCredentials(String passwordHash, String accessToken, ) {
        super(AuthProvider.LOCAL);
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }
    
}
