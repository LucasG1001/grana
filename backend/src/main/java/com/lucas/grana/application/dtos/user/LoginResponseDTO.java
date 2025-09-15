package com.lucas.grana.application.dtos.user;

public record LoginResponseDTO(String email, String accessToken, String refreshToken) {
}