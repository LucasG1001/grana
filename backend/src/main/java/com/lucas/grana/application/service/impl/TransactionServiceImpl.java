package com.lucas.grana.application.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lucas.grana.application.service.TransactionService;
import com.lucas.grana.domain.Transaction;
import com.lucas.grana.infra.persistence.TransactionRepository;
import com.lucas.grana.infra.persistence.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        transaction.setUser(user);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    
}
