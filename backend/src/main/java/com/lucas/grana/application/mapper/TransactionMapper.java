package com.lucas.grana.application.mapper;

import java.util.List;

import com.lucas.grana.application.dto.transaction.CreateTransactionDTO;
import com.lucas.grana.application.dto.transaction.TransactionResponseDTO;
import com.lucas.grana.application.dto.transaction.UpdateTransactionDTO;
import com.lucas.grana.domain.Category;
import com.lucas.grana.domain.Transaction;
import com.lucas.grana.domain.User;

public interface TransactionMapper {

    Transaction fromCreate(CreateTransactionDTO createTransactionDTO, User user, Category category);

    Transaction fromUpdate(UpdateTransactionDTO updateTransactionDTO, User user, Category category);

    TransactionResponseDTO toTransactionResponse(Transaction transaction);

    List<TransactionResponseDTO> toTransactionResponseList(List<Transaction> transactions);
}
