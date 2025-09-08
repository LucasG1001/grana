package com.lucas.grana.domain.valueObjects.category;

import com.lucas.grana.domain.validators.MaxLengthValidator;
import com.lucas.grana.domain.validators.MinLengthValidator;
import com.lucas.grana.domain.validators.NotNullValidator;

public record CategoryName(String value) {

    private static final String FIELD_NAME = "Category";

    private static final NotNullValidator NOT_NULL = new NotNullValidator(FIELD_NAME);

    private static final MaxLengthValidator MAX_LENGTH = new MaxLengthValidator(30, FIELD_NAME);

    private static final MinLengthValidator MIN_LENGTH = new MinLengthValidator(3, FIELD_NAME);

    public CategoryName {
        NOT_NULL.validate(value);
        MAX_LENGTH.validate(value);
        MIN_LENGTH.validate(value);
    }
}