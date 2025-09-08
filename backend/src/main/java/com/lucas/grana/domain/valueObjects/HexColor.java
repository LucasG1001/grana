package com.lucas.grana.domain.valueObjects;

import com.lucas.grana.domain.validators.MatchesValidator;
import com.lucas.grana.domain.validators.NotNullValidator;

public record HexColor(String value) {

    private static final String FIELD_NAME = "HexColor";

    private static final NotNullValidator NOT_NULL = new NotNullValidator(FIELD_NAME);
    private static final MatchesValidator MATCHES = new MatchesValidator(FIELD_NAME,
            "^#(?:[0-9a-fA-F]{3}|[0-9a-fA-F]{6}|[0-9a-fA-F]{4}|[0-9a-fA-F]{8})$");

    public HexColor {
        NOT_NULL.validate(value);
        MATCHES.validate(value);
    }
}