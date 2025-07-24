package com.lucas.grana.application.mapper.Transaction;

import com.lucas.grana.application.dto.transaction.UpdateTransactionDTO;
import com.lucas.grana.domain.Category;
import com.lucas.grana.domain.Transaction;
import com.lucas.grana.domain.User;

public class UpdateTransactionMapper {
        public static Transaction toTransaction(UpdateTransactionDTO dto, User user, Category category) {
        return Transaction.builder()
            .id(dto.getId())
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
