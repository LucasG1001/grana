package com.lucas.grana.domain.validators;


public class EmailValidator  implements Validator<String> {

    @Override
    public void validate(String value) {
        if(!value.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")){
            throw new IllegalArgumentException("Email não e válido");
        }
    }
    
}
