package com.lucas.grana.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale.Category;

import com.lucas.grana.application.enums.TransactionType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TransactionResponseDTO {
        private String id;

    private TransactionType type;

    private String description;

    private BigDecimal value;

    private LocalDate date;

    private Boolean installment = false;

    private Integer currentInstallment;

    private Integer totalInstallments;

    private CategoryResponseDTO category;
}


