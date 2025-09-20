package com.lucas.grana.domain.exceptions.token;

import com.lucas.grana.domain.exceptions.NotGeneratedException;

public class AuthTokenNotGeneratedException extends NotGeneratedException {

    public AuthTokenNotGeneratedException(String email) {
        super("Nenhum token gerado para o usuário: " + email);
    }

}
