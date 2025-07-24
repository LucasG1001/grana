package com.lucas.grana.application.mapper.Transaction.impl;

import org.springframework.stereotype.Component;

import com.lucas.grana.application.dto.transaction.CreateTransactionDTO;
import com.lucas.grana.application.dto.transaction.TransactionResponseDTO;
import com.lucas.grana.application.dto.transaction.UpdateTransactionDTO;
import com.lucas.grana.application.mapper.Transaction.CreateTransactionMapper;
import com.lucas.grana.application.mapper.Transaction.TransactionMapper;
import com.lucas.grana.application.mapper.Transaction.TransactionResponseMapper;
import com.lucas.grana.application.mapper.Transaction.UpdateTransactionMapper;
import com.lucas.grana.domain.Category;
import com.lucas.grana.domain.Transaction;
import com.lucas.grana.domain.User;

@Component
public class DefaultTransactionMapper  implements TransactionMapper {
    @Override
    public Transaction fromCreate(CreateTransactionDTO dto, User user, Category category) {
        return CreateTransactionMapper.toTransaction(dto, user, category);
    }
    @Override
    public Transaction fromUpdate(UpdateTransactionDTO dto, User user, Category category) {
        return UpdateTransactionMapper.toTransaction(dto, user, category);
    }
    @Override
    public TransactionResponseDTO toTransactionResponse(Transaction transaction) {
        return TransactionResponseMapper.toTransactionResponse(transaction);
    }
}
