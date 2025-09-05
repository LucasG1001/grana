package com.lucas.grana.domain.entities.credentials;

import com.lucas.grana.domain.enums.user.AuthProvider;

public abstract class Credentials {

    private final AuthProvider authProvider;

    public Credentials(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    public AuthProvider getProvider() {
        return this.authProvider;
    }
    
}
