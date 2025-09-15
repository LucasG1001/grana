package com.lucas.grana.application.security;

import com.lucas.grana.domain.entities.User;

public interface AuthService {

    public User authenticate(String email, String password);
}