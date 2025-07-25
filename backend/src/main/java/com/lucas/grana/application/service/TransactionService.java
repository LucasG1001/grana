package com.lucas.grana.application.service;

import java.time.LocalDate;
import java.util.List;

import com.lucas.grana.application.dto.transaction.CreateTransactionDTO;
import com.lucas.grana.application.dto.transaction.TransactionResponseDTO;
import com.lucas.grana.application.dto.transaction.UpdateTransactionDTO;

public interface TransactionService {
    TransactionResponseDTO createTransaction(CreateTransactionDTO transaction);

    TransactionResponseDTO updateTransaction(UpdateTransactionDTO transaction, String id);

    List<TransactionResponseDTO> findByAuthenticatedUser();

    List<TransactionResponseDTO> findByAuthenticatedUserByDateRange(LocalDate startDate, LocalDate endDate);

}
