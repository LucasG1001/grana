package com.lucas.grana.domain.validators;

public class MatchesValidator implements Validator<String> {

    private final String fieldName;
    private final String regex;
    
    public MatchesValidator(String fieldName, String regex) {
        this.fieldName = fieldName;
        this.regex = regex;
    }

    @Override
    public void validate(String value) {
        if(!value.matches(regex)) {
            throw new IllegalArgumentException(fieldName + " não e válido");
        }
    }
    
}
