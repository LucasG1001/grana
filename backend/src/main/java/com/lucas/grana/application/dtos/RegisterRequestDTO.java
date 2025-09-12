package com.lucas.grana.application.dtos;

import com.lucas.grana.domain.enums.user.UserRole;

public record RegisterRequestDTO(String userName,
                String email,
                UserRole role) {
}
