package com.lucas.grana.infra.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lucas.grana.application.dto.CreateTransactionDTO;
import com.lucas.grana.application.mapper.TransactionMapper;
import com.lucas.grana.application.service.TransactionService;
import com.lucas.grana.domain.Transaction;
import com.lucas.grana.infra.persistence.CategoryRepository;
import com.lucas.grana.infra.persistence.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction get(@RequestBody @Valid CreateTransactionDTO transaction) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 

        String email = auth.getName();

        var category = categoryRepository.findByName(transaction.getCategoryName());

    if (category == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada");
    }

        Transaction transactionWithUser = TransactionMapper.toTransaction(transaction, userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found")), category);

        return transactionService.createTransaction(transactionWithUser);
    }

    @GetMapping()
    public List<Transaction> getAllTransactionsByUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
        String email = auth.getName();

        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        return transactionService.findByUserId(user.getId());
    }

}
