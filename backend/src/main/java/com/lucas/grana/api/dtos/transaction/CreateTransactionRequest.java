package com.lucas.grana.api.dtos.transaction;

public record CreateTransactionRequest(
                String description,
                Double amount,
                String date,
                String categoryId,
                String type) {
}