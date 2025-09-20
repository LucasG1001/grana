package com.lucas.grana.domain.valueObjects.security;

import com.lucas.grana.domain.validators.BetweenLengthValidator;
import com.lucas.grana.domain.validators.NotNullValidator;

public record TokenValue(String value) {

    private static final String FIELD_NAME = "TokenValue";
    private static final NotNullValidator NOT_NULL = new NotNullValidator(FIELD_NAME);
    private static final BetweenLengthValidator BETWEEN_LENGTH = new BetweenLengthValidator(FIELD_NAME, 8, 60);

    public TokenValue {
        NOT_NULL.validate(value);
        BETWEEN_LENGTH.validate(value);
    }

    @Override
    public final String toString() {
        return value;
    }
}