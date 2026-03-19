package com.finance.domain.repository;

import com.finance.domain.model.Transaction;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    Optional<Transaction> findById(Long id);
    List<Transaction> findAll();
    List<Transaction> findByMonthAndYear(int month, int year);
    void deleteById(Long id);
}
