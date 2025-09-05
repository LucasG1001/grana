package com.lucas.grana.infrastructure.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import com.lucas.grana.application.dto.transaction.CreateTransactionDTO;
import com.lucas.grana.application.dto.transaction.TransactionResponseDTO;
import com.lucas.grana.application.dto.transaction.UpdateTransactionDTO;
import com.lucas.grana.application.service.TransactionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponseDTO get(@RequestBody @Valid CreateTransactionDTO transaction) {
        return transactionService.createTransaction(transaction);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponseDTO update(@PathVariable String id,
            @RequestBody @Valid UpdateTransactionDTO dto) {

        return transactionService.updateTransaction(dto, id);
    }

    @GetMapping()
    public List<TransactionResponseDTO> getAllTransactionsByUserId() {
        return transactionService.findByAuthenticatedUser();
    }

    @GetMapping("/date-between")
    public List<TransactionResponseDTO> getAllTransactionsByUserIdAndDateBetween(@RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        var transactions = transactionService.findByAuthenticatedUserByDateRange(startDate, endDate);

        return transactions;
    }

}
