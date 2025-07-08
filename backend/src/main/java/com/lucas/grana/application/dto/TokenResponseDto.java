package com.lucas.grana.application.dto;

public record TokenResponseDto (
    String accessToken,
    String refreshToken
){}
