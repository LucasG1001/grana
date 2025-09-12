package com.lucas.grana.domain.valueObjects;

import com.lucas.grana.domain.validators.MaxLengthValidator;
import com.lucas.grana.domain.validators.NotNullValidator;

public record Icon(String value) {

    private static final String FIELD_NAME = "Icon";

    private static final NotNullValidator NOT_NULL = new NotNullValidator(FIELD_NAME);
    private static final MaxLengthValidator MAX_LENGTH = new MaxLengthValidator(255, FIELD_NAME);

    public Icon {
        NOT_NULL.validate(value);
        MAX_LENGTH.validate(value);
    }
}