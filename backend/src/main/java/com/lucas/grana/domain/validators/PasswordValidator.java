package com.lucas.grana.domain.validators;

public class PasswordValidator implements Validator<String> {

    @Override
    public void validate(String value) {
        if (!value.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos uma letra maiúscula");
        }
        if (!value.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos um número");
        }
    }

}
