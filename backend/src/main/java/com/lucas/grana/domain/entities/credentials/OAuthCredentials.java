package com.lucas.grana.domain.entities.credentials;

import com.lucas.grana.domain.enums.user.AuthProvider;

public class OAuthCredentials extends Credentials {
    private final String externalId;
    private final String accessToken;

    public OAuthCredentials(AuthProvider provider, String externalId, String accessToken) {
        super(provider);
        this.externalId = externalId;
        this.accessToken = accessToken;
    }

    public String getExternalId() {
        return this.externalId;
    }

    public String getAccessToken(){
        return this.accessToken;
    }
    
}
