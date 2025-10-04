package com.lucas.grana.application.security;

public interface PasswordEncoder {

    String encode(String rawPassword);

    boolean matches(String rawPassoword, String encodedPassword);
}