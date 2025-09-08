package com.lucas.grana.domain.validators;

public class MinLengthValidator implements Validator<String> {

    private final int min;
    private final String fieldName;

    public MinLengthValidator(int min, String fieldName) {
        this.min = min;
        this.fieldName = fieldName;
    }

    @Override
    public void validate(String value) {
        if (value.length() < min) {
            throw new IllegalArgumentException(fieldName + " não pode ter menos de " + min + " caracteres");
        }
    }

}
