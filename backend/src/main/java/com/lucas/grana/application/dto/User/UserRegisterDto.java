package com.lucas.grana.application.dto.User;

import com.lucas.grana.domain.enums.user.UserRole;

public record UserRegisterDto(String email, String password, UserRole role) {
}
