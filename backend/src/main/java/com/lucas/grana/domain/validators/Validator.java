package com.lucas.grana.domain.validators;

public interface Validator<T> {
    void validate(T value);
}

