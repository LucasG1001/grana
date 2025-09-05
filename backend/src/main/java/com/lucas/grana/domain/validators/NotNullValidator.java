package com.lucas.grana.domain.validators;

public class NotNullValidator implements Validator<Object> {

    private final String fieldName;

    public NotNullValidator(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public void validate(Object value) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " não pode ser nulo");
        }
    }

}
