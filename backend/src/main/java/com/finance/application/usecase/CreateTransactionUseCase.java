package com.finance.application.usecase;

import com.finance.domain.model.Transaction;
import com.finance.domain.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public CreateTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction execute(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
