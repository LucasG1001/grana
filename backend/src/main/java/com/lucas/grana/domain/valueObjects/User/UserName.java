package com.lucas.grana.domain.valueObjects.User;

import com.lucas.grana.domain.validators.MaxLengthValidator;
import com.lucas.grana.domain.validators.NotEmptyValidator;
import com.lucas.grana.domain.validators.Validator;

public record UserName(String value) {

    private static final String FIELD_NAME = "UserName";
    private static final Validator<String> NOT_EMPTY = new NotEmptyValidator(FIELD_NAME);
    private static final Validator<String> MAX_LENGTH = new MaxLengthValidator(60, FIELD_NAME);

    public UserName {
        NOT_EMPTY.validate(value);
        MAX_LENGTH.validate(value);
        value = value.trim();
    }
}