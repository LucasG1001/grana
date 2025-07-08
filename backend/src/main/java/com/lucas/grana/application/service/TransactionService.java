package com.lucas.grana.application.service;

import java.util.List;

import com.lucas.grana.domain.transaction.Transaction;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
}
