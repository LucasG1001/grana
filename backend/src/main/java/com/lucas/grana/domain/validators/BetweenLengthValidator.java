package com.lucas.grana.domain.validators;

public class BetweenLengthValidator implements Validator<String> {

    private final String fieldName;
    private final int min;
    private final int max;

    public BetweenLengthValidator(String fieldName, int min, int max) {
        this.fieldName = fieldName;
        this.min = min;
        this.max = max;
    }

    @Override
    public void validate(String value) {
        if (value.length() < min || value.length() > max) {
            throw new IllegalArgumentException(fieldName + " deve ter entre " + min + " e " + max + " caracteres");
        }
    }

}
