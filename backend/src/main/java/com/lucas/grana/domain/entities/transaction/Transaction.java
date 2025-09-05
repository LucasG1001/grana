package com.lucas.grana.domain.entities.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.lucas.grana.application.enums.TransactionType;
import com.lucas.grana.domain.entities.BaseEntity;
import com.lucas.grana.domain.entities.Category;
import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.valueObjects.Transaction.Money;

public class Transaction extends BaseEntity {

    private TransactionType type;
    private String description;
    private Money value;
    private LocalDateTime date;
    private Category category;
    private User user;
    private final Installments installments;

    public Transaction(TransactionType type, String description, Money value, LocalDateTime date, Category category,
            User user, Installments installment) {
        this.type = type;
        this.description = description;
        this.value = value;
        this.date = date != null ? date : LocalDateTime.now();
        this.category = category;
        this.user = user;
        this.installments = installment;
    }

    public Installments getInstallments() {
        return installments;
    }

    public TransactionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    public Boolean getInstallment() {
        return installment;
    }

    public Integer getCurrentInstallment() {
        return currentInstallment;
    }

    public Integer getTotalInstallments() {
        return totalInstallments;
    }

    public Category getCategory() {
        return category;
    }

    public User getUser() {
        return user;
    }
}