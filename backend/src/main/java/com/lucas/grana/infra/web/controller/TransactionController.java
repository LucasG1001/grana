package com.lucas.grana.infra.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lucas.grana.application.dto.transaction.CreateTransactionDTO;
import com.lucas.grana.application.dto.transaction.TransactionResponseDTO;
import com.lucas.grana.application.dto.transaction.UpdateTransactionDTO;
import com.lucas.grana.application.mapper.Transaction.TransactionMapper;
import com.lucas.grana.application.mapper.Transaction.TransactionResponseMapper;
import com.lucas.grana.application.service.TransactionService;
import com.lucas.grana.domain.Transaction;
import com.lucas.grana.domain.User;
import com.lucas.grana.infra.persistence.CategoryRepository;
import com.lucas.grana.infra.persistence.UserRepository;
import com.lucas.grana.infra.user.AuthenticatedUserProvider;
import com.lucas.grana.infra.user.UserContext;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionMapper transactionMapper;
    private final AuthenticatedUserProvider userProvider;
    
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponseDTO get(@RequestBody @Valid CreateTransactionDTO transaction) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 

        String email = auth.getName();
        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        var category = categoryRepository.findByUserId(user.getId()).stream().filter(c -> c.getName().equalsIgnoreCase(transaction.getCategoryName())).findFirst().orElse(null);

    if (category == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada");
    }

        Transaction transactionWithUser = TransactionMapper.toTransaction(transaction, user, category);

        TransactionResponseDTO transactionResponse = transactionService.createTransaction(transactionWithUser);

        return transactionResponse;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponseDTO update(@PathVariable String id, @RequestBody @Valid UpdateTransactionDTO transaction) {
        transaction.setId(id);
        User user = userProvider.getAuthenticatedUser();
        Transaction transactionWithUser = transactionMapper.fromUpdate(transaction, user, categoryRepository.findByUserId(user.getId()).stream().filter(c -> c.getName().equalsIgnoreCase(transaction.getCategoryName())).findFirst().orElse(null));

        return transactionMapper.toTransactionResponse(transactionWithUser);
    }

    @GetMapping()
    public List<TransactionResponseDTO> getAllTransactionsByUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
        String email = auth.getName();

        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        var transactions = transactionService.findByUserId(user.getId());

        return transactions;
    }

    @GetMapping("/date-between")
    public List<TransactionResponseDTO> getAllTransactionsByUserIdAndDateBetween(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
        String email = auth.getName();

        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        var transactions = transactionService.findByDateBetween(user.getId(), startDate, endDate);

        return transactions;
    }

}
