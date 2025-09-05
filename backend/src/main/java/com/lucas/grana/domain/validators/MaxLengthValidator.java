package com.lucas.grana.domain.validators;

public class MaxLengthValidator implements Validator<String>{

    private final int max;
    private final String fieldName;

    public MaxLengthValidator(int max, String fieldName) {
        this.max = max;
        this.fieldName = fieldName;
    }

    @Override
    public void validate(String value) {
        if(value.length() > max) {
            throw new IllegalArgumentException(fieldName + " não pode ter mais de " + max + " caracteres");
        }
    }
    
}
