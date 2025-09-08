package com.lucas.grana.domain.entities.transaction;

import java.time.LocalDateTime;
import java.util.Objects;

import com.lucas.grana.application.enums.TransactionType;
import com.lucas.grana.domain.entities.BaseEntity;
import com.lucas.grana.domain.entities.Category;
import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.valueObjects.Money;

public class Transaction extends BaseEntity {

    private TransactionType type;
    private String description;
    private Money value;
    private LocalDateTime dateTime;
    private Category category;
    private User user;
    private final Installments installments;

    public Transaction(TransactionType type, String description, Money value, LocalDateTime dateTime, Category category,
            User user, Installments installments) {
        this.type = Objects.requireNonNull(type, "TransactionType  não pode ser nulo");
        this.description = description;
        this.value = value;
        this.dateTime = dateTime != null ? dateTime : LocalDateTime.now();
        this.category = category;
        this.user = Objects.requireNonNull(user, "User  não pode ser nulo");
        this.installments = installments;
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

    public Money getValue() {
        return value;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Installments getInstallment() {
        return installments;
    }

    public Category getCategory() {
        return category;
    }

    public User getUser() {
        return user;
    }
}