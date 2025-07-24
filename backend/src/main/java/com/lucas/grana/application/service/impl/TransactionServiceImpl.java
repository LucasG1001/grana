package com.lucas.grana.application.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lucas.grana.application.dto.transaction.TransactionResponseDTO;
import com.lucas.grana.application.mapper.Transaction.TransactionResponseMapper;
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
    public TransactionResponseDTO createTransaction(Transaction transaction) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        transaction.setUser(user);

        transactionRepository.save(transaction);

        return TransactionResponseMapper.toTransactionResponse(transaction);
    }

    @Override
    public List<TransactionResponseDTO> getAllTransactions() {
        return TransactionResponseMapper.toTransactionResponseList(transactionRepository.findAll());
    }

    @Override
    public List<TransactionResponseDTO> findByUserId(String userId) {
        return TransactionResponseMapper.toTransactionResponseList(transactionRepository.findByUserId(userId));
    }

    @Override
    public List<TransactionResponseDTO> findByDateBetween(String userId, LocalDate startDate, LocalDate endDate) {
        return TransactionResponseMapper.toTransactionResponseList(transactionRepository.findByDateBetween(userId, startDate, endDate));
    }
}
