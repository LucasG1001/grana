package com.lucas.grana.domain.valueObjects.category;

import com.lucas.grana.domain.validators.BetweenLengthValidator;
import com.lucas.grana.domain.validators.NotNullValidator;

public record CategoryName(String value) {

    private static final String FIELD_NAME = "Category";

    private static final NotNullValidator NOT_NULL = new NotNullValidator(FIELD_NAME);

    private static final BetweenLengthValidator BETWEEN_LENGTH = new BetweenLengthValidator(FIELD_NAME, 3, 30);

    public CategoryName {
        NOT_NULL.validate(value);
        BETWEEN_LENGTH.validate(value);
    }
}