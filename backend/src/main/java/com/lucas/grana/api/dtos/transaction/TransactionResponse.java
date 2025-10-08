package com.lucas.grana.api.dtos.transaction;

import com.lucas.grana.api.dtos.category.CategoryResponse;

public record TransactionResponse(String description,
                Double amount,
                String date,
                CategoryResponse category,
                String type) {
}