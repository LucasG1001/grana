package com.lucas.grana.application.dto.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.lucas.grana.application.enums.TransactionType;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTransactionDTO {

    @NotNull
    private TransactionType type;

    @NotBlank
    @Size(max = 100)
    private String description;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal value;

    @NotNull
    private String categoryName;

    @NotNull
    private LocalDate date;

    @Min(1)
    private Boolean installment;

    @Min(1)
    private Integer currentInstallment;

    @Min(1)
    private Integer totalInstallments;
}
