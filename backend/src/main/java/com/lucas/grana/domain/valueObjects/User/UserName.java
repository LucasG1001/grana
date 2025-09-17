package com.lucas.grana.domain.valueObjects.User;

import com.lucas.grana.domain.validators.BetweenLengthValidator;
import com.lucas.grana.domain.validators.NotEmptyValidator;
import com.lucas.grana.domain.validators.Validator;

public record UserName(String value) {

    private static final String FIELD_NAME = "UserName";
    private static final Validator<String> NOT_EMPTY = new NotEmptyValidator(FIELD_NAME);
    private static final Validator<String> BETWEEN_LENGTH = new BetweenLengthValidator(FIELD_NAME, 3, 30);

    public UserName {
        NOT_EMPTY.validate(value);
        BETWEEN_LENGTH.validate(value);
        value = value.trim();
    }

    @Override
    public String toString() {
        return value;
    }
}