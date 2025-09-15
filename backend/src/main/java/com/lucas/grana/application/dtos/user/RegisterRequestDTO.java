package com.lucas.grana.application.dtos.user;

public record RegisterRequestDTO(String userName,
        String email,
        String password) {
}
