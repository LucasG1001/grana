package com.lucas.grana.api.dtos;

public record CreateTransactionRequest(
        String description,
        Double amount,
        String date,
        String categoryId,
        String type) {
}