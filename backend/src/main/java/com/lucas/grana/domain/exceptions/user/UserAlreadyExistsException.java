package com.lucas.grana.domain.exceptions.user;

import com.lucas.grana.domain.exceptions.AlreadyExistsException;

public class UserAlreadyExistsException extends AlreadyExistsException {

    public UserAlreadyExistsException(String email) {
        super("Usuário com email" + email + " já existe.");
    }

}