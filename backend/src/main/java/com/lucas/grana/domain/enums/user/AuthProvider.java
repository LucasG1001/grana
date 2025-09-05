package com.lucas.grana.domain.enums.user;

public enum AuthProvider {

    LOCAL("local"),
    GOOGLE("google");

    private String provider;

    AuthProvider(String provider){
        this.provider = provider;
    }

    public String getProvider() {
        return this.provider;
    }

}