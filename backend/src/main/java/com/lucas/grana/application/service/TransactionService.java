package com.lucas.grana.application.service;

import java.time.LocalDate;
import java.util.List;

import com.lucas.grana.application.dto.TransactionResponseDTO;
import com.lucas.grana.domain.Transaction;

public interface TransactionService {
    TransactionResponseDTO createTransaction(Transaction transaction);
    List<TransactionResponseDTO> getAllTransactions();
    List<TransactionResponseDTO> findByUserId(String userId);
    List<TransactionResponseDTO> findByDateBetween(String userId, LocalDate startDate, LocalDate endDate);
}
