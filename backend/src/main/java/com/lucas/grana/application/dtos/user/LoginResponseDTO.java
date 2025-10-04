package com.lucas.grana.application.dtos.user;

public record LoginResponseDTO(String userName, String email, String accessToken, String refreshToken) {
}