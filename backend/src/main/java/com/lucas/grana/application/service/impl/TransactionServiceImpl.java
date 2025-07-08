package com.lucas.grana.application.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lucas.grana.application.service.TransactionService;
import com.lucas.grana.domain.transaction.Transaction;
import com.lucas.grana.infra.persistence.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    
}
