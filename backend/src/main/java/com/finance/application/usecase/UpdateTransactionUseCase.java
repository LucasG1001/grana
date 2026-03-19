package com.finance.application.usecase;

import com.finance.domain.model.Transaction;
import com.finance.domain.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public UpdateTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction execute(Long id, Transaction transactionData) {
        Transaction existing = transactionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Transaction not found"));
        
        existing.setValor(transactionData.getValor());
        existing.setCategoria(transactionData.getCategoria());
        existing.setDescricao(transactionData.getDescricao());
        existing.setData(transactionData.getData());
        existing.setTipo(transactionData.getTipo());
        
        return transactionRepository.save(existing);
    }
}
