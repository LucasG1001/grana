package com.lucas.grana.application.security;

import com.lucas.grana.domain.entities.User;

public interface TokenProvider {
    public String generateAccessToken(User user);

    public String generateRefreshToken(User user);
}
