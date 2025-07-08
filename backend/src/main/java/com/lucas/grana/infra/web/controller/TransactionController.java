package com.lucas.grana.infra.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lucas.grana.application.mapper.TransactionMapper;
import com.lucas.grana.application.service.TransactionService;
import com.lucas.grana.domain.transaction.Transaction;
import com.lucas.grana.dto.CreateTransactionDTO;
import com.lucas.grana.repository.user.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final UserRepository userRepository;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction get(@RequestBody @Valid CreateTransactionDTO transaction) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 

        String email = auth.getName();

        Transaction transactionWithUser = TransactionMapper.toTransaction(transaction, userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found")));

        return transactionService.createTransaction(transactionWithUser);
    }

    @GetMapping()
    public List<Transaction> getAll() {
        return transactionService.getAllTransactions();
    }

}
