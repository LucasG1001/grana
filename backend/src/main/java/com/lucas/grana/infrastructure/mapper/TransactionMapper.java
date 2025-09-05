package com.lucas.grana.infrastructure.mapper;

import java.util.List;

import com.lucas.grana.application.dto.transaction.CreateTransactionDTO;
import com.lucas.grana.application.dto.transaction.TransactionResponseDTO;
import com.lucas.grana.application.dto.transaction.UpdateTransactionDTO;
import com.lucas.grana.domain.entities.Category;
import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.entities.transaction.Transaction;

public interface TransactionMapper {

    Transaction fromCreate(CreateTransactionDTO createTransactionDTO, User user, CategoryEntity category);

    Transaction fromUpdate(UpdateTransactionDTO updateTransactionDTO, User user, CategoryEntity category);

    TransactionResponseDTO toTransactionResponse(Transaction transaction);

    List<TransactionResponseDTO> toTransactionResponseList(List<Transaction> transactions);
}
