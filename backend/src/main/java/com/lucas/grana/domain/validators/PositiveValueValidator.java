package com.lucas.grana.domain.validators;

import java.math.BigDecimal;

public class PositiveValueValidator implements Validator<BigDecimal> {

    private final String fieldName;

    public PositiveValueValidator(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public void validate(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(fieldName + "precisa ser positivo");
        }
    }

}
