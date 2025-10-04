package com.lucas.grana.application.security;

public interface PasswordHasher {

    String encode(String rawPassword);

    boolean matches(String rawPassoword, String encodedPassword);
}