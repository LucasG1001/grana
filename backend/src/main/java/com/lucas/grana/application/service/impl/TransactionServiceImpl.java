package com.lucas.grana.application.service.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

import com.lucas.grana.application.dto.transaction.CreateTransactionDTO;
import com.lucas.grana.application.dto.transaction.TransactionResponseDTO;
import com.lucas.grana.application.dto.transaction.UpdateTransactionDTO;
import com.lucas.grana.application.service.CategoryService;
import com.lucas.grana.application.service.TransactionService;
import com.lucas.grana.domain.entities.Category;
import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.entities.transaction.Transaction;
import com.lucas.grana.infra.persistence.TransactionRepository;
import com.lucas.grana.infra.user.AuthenticatedUserProvider;
import com.lucas.grana.infrastructure.mapper.TransactionMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AuthenticatedUserProvider userProvider;
    private final CategoryService categoryService;

    @Override
    public TransactionResponseDTO createTransaction(CreateTransactionDTO dto) {
        User user = userProvider.getAuthenticatedUser();

        CategoryEntity category = categoryService.getCategoryByUserIdAndCategoryName(user.getId(),
                dto.getCategoryName());

        Transaction transaction = transactionMapper.fromCreate(dto, user, category);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return transactionMapper.toTransactionResponse(savedTransaction);
    }

    @Override
    public TransactionResponseDTO updateTransaction(UpdateTransactionDTO dto, String id) {
        User user = userProvider.getAuthenticatedUser();
        CategoryEntity category = categoryService.getCategoryByUserIdAndCategoryName(user.getId(),
                dto.getCategoryName());
        Transaction transaction = transactionMapper.fromUpdate(dto, user, category);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return transactionMapper.toTransactionResponse(savedTransaction);
    }

    @Override
    public List<TransactionResponseDTO> findByAuthenticatedUser() {
        String userId = userProvider.getAuthenticatedUser().getId();
        return transactionMapper.toTransactionResponseList(transactionRepository.findByUserId(userId));
    }

    @Override
    public List<TransactionResponseDTO> findByAuthenticatedUserByDateRange(LocalDate startDate, LocalDate endDate) {
        String userId = userProvider.getAuthenticatedUser().getId();
        return transactionMapper
                .toTransactionResponseList(transactionRepository.findByDateBetween(userId, startDate, endDate));
    }
}
