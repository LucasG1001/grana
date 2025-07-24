package com.lucas.grana.application.mapper.Transaction;

import com.lucas.grana.application.dto.transaction.CreateTransactionDTO;
import com.lucas.grana.domain.Category;
import com.lucas.grana.domain.Transaction;
import com.lucas.grana.domain.User;

public class CreateTransactionMapper {
    
    public static Transaction toTransaction(CreateTransactionDTO dto, User user, Category category) {
        return Transaction.builder()
            .type(dto.getType())
            .description(dto.getDescription())
            .value(dto.getValue())
            .date(dto.getDate())
            .installment(dto.getInstallment() != null ? dto.getInstallment() : false)
            .currentInstallment(dto.getCurrentInstallment())
            .totalInstallments(dto.getTotalInstallments())
            .user(user)
            .category(category)
            .build();
    }
}
