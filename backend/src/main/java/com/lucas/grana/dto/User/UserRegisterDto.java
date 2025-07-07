package com.lucas.grana.dto.User;

import com.lucas.grana.domain.user.UserRole;

public record UserRegisterDto(String email, String password, UserRole role) {}
