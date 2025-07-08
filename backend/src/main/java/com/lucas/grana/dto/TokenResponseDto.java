package com.lucas.grana.dto;

public record TokenResponseDto (
    String accessToken,
    String refreshToken
){}
