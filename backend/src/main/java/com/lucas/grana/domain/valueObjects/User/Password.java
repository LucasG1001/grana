package com.lucas.grana.domain.valueObjects.User;

import com.lucas.grana.domain.validators.BetweenLengthValidator;
import com.lucas.grana.domain.validators.NotNullValidator;
import com.lucas.grana.domain.validators.PasswordValidator;

public record Password(String value) {

    private static final String FIELD_NAME = "Password";
    private static final NotNullValidator NOT_NULL = new NotNullValidator(FIELD_NAME);
    private static final BetweenLengthValidator BETWEEN_LENGTH = new BetweenLengthValidator(FIELD_NAME, 8, 100);
    private static final PasswordValidator PASSWORD = new PasswordValidator();

    public Password {
        NOT_NULL.validate(value);
        BETWEEN_LENGTH.validate(value);
        PASSWORD.validate(value);
    }

}