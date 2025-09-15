package com.lucas.grana.domain.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String email) {
        super("Usuário com email" + email + " já existe.");
    }

}