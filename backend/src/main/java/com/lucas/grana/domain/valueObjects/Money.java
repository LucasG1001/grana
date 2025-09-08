package com.lucas.grana.domain.valueObjects;

import java.math.BigDecimal;

import com.lucas.grana.domain.validators.NotNullValidator;
import com.lucas.grana.domain.validators.PositiveValueValidator;

public record Money(BigDecimal amount) {

    private static final String FIELD_NAME = "MONEY";

    private static final NotNullValidator NOT_NULL = new NotNullValidator(FIELD_NAME);
    private static final PositiveValueValidator POSITIVE_VALUE = new PositiveValueValidator(FIELD_NAME);

    public Money {
        NOT_NULL.validate(amount);
        POSITIVE_VALUE.validate(amount);
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
