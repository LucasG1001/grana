package com.lucas.grana.application.mapper;

import com.lucas.grana.application.dto.CreateTransactionDTO;
import com.lucas.grana.domain.Transaction;
import com.lucas.grana.domain.User;

public class TransactionMapper {

    public static Transaction toTransaction(CreateTransactionDTO createTransactionDTO, User user) {
        return Transaction.builder()
            .type(createTransactionDTO.getType())
            .description(createTransactionDTO.getDescription())
            .value(createTransactionDTO.getValue())
            .date(createTransactionDTO.getDate())
            .installment(createTransactionDTO.getInstallment() != null ? createTransactionDTO.getInstallment() : false)
            .currentInstallment(createTransactionDTO.getCurrentInstallment())
            .totalInstallments(createTransactionDTO.getTotalInstallments())
            .user(user)
            .build();
    }
    
}
