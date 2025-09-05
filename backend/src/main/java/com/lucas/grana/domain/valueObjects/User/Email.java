package com.lucas.grana.domain.valueObjects.User;

public record Email(String value) {

    public Email {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email não pode ser nulo ou vazio");
        }
        if (value.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Email inválido");
        }

        value = value.toLowerCase();
    }

    @Override
    public String toString() {
        return value;
    }
}
