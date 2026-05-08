package com.finance.application.usecase;

import com.finance.domain.model.Transaction;
import com.finance.domain.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public CreateTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction execute(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> executeAll(List<Transaction> transactions) {
        return transactions.stream()
            .map(transactionRepository::save)
            .toList();
    }
}
