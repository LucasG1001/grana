package com.lucas.grana.application.mapper;

import java.util.List;

import com.lucas.grana.application.dto.TransactionResponseDTO;
import com.lucas.grana.domain.Transaction;

public class TransactionResponseMapper {

    public static TransactionResponseDTO toTransactionResponse(Transaction transaction) {
        return TransactionResponseDTO.builder()
            .id(transaction.getId())
            .type(transaction.getType())
            .description(transaction.getDescription())
            .value(transaction.getValue())
            .date(transaction.getDate())
            .installment(transaction.getInstallment())
            .currentInstallment(transaction.getCurrentInstallment())
            .totalInstallments(transaction.getTotalInstallments())
            .categoryName(transaction.getCategory().getName())
            .categoryColor(transaction.getCategory().getColor())
            .build();

    }

    public static List<TransactionResponseDTO> toTransactionResponseList(List<Transaction> transactions) {
        return transactions.stream().map(TransactionResponseMapper::toTransactionResponse).toList();
    }
    
}
