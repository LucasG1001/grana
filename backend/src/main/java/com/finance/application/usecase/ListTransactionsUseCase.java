package com.finance.application.usecase;

import com.finance.domain.model.Transaction;
import com.finance.domain.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListTransactionsUseCase {

    private final TransactionRepository transactionRepository;

    public ListTransactionsUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> execute() {
        return transactionRepository.findAll();
    }

    public List<Transaction> executeByMonth(int month, int year) {
        return transactionRepository.findByMonthAndYear(month, year);
    }
}
