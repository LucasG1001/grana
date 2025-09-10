package com.lucas.grana.domain.entities;

import com.lucas.grana.domain.enums.user.AuthProvider;

public class AuthCredentials {
    private final String externalId;
    private final AuthProvider provider;
    private final String passwordHash;
    private final String refreshToken;
    
    public AuthCredentials(String externalId, AuthProvider provider, String passwordHash, String refreshToken) {
        this.externalId = externalId;
        this.provider = provider;
        this.passwordHash = passwordHash;
        this.refreshToken = refreshToken;
    }

    public String getExternalId() {
        return externalId;
    }
    public AuthProvider getProvider() {
        return provider;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
    
}