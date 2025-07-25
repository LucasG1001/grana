package com.lucas.grana.application.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lucas.grana.application.dto.transaction.CreateTransactionDTO;
import com.lucas.grana.application.dto.transaction.TransactionResponseDTO;
import com.lucas.grana.application.dto.transaction.UpdateTransactionDTO;
import com.lucas.grana.application.mapper.CategoryResponseMapper;
import com.lucas.grana.application.mapper.TransactionMapper;
import com.lucas.grana.domain.Category;
import com.lucas.grana.domain.Transaction;
import com.lucas.grana.domain.User;

@Component
public class TransactionMapperImpl implements TransactionMapper {
    @Override
    public Transaction fromCreate(CreateTransactionDTO dto, User user, Category category) {
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

    @Override
    public Transaction fromUpdate(UpdateTransactionDTO dto, User user, Category category) {
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

    @Override
    public TransactionResponseDTO toTransactionResponse(Transaction transaction) {
        return TransactionResponseDTO.builder()
                .id(transaction.getId())
                .type(transaction.getType())
                .description(transaction.getDescription())
                .value(transaction.getValue())
                .date(transaction.getDate())
                .installment(transaction.getInstallment())
                .currentInstallment(transaction.getCurrentInstallment())
                .totalInstallments(transaction.getTotalInstallments())
                .category(CategoryResponseMapper.toCategoryResponse(transaction.getCategory()))
                .build();

    }

    @Override
    public List<TransactionResponseDTO> toTransactionResponseList(List<Transaction> transactions) {
        return transactions.stream().map(this::toTransactionResponse).toList();
    }

}
