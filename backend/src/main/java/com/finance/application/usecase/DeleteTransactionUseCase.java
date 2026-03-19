package com.finance.application.usecase;

import com.finance.domain.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public DeleteTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void execute(Long id) {
        transactionRepository.deleteById(id);
    }
}
