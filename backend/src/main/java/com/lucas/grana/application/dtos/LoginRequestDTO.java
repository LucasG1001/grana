package com.lucas.grana.application.dtos;

import com.lucas.grana.domain.valueObjects.User.Email;

public record LoginRequestDTO(String userName, Email email, String password) {
}
