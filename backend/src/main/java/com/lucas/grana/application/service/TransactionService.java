package com.lucas.grana.application.service;

import java.util.List;

import com.lucas.grana.domain.Transaction;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
    List<Transaction> findByUserId(String userId);
}
