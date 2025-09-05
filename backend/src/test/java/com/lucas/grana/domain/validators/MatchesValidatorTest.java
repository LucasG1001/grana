package com.lucas.grana.domain.validators;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class MatchesValidatorTest {

    @Test
    void shouldPassValidationWhenValueIsNotEmply() {
        NotEmptyValidator validator = new NotEmptyValidator("nome");
        assertDoesNotThrow(() -> validator.validate("lucas"));
    }
    
}
