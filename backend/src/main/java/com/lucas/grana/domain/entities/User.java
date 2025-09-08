package com.lucas.grana.domain.entities;

import java.util.List;
import java.util.Objects;

import com.lucas.grana.domain.entities.credentials.Credentials;
import com.lucas.grana.domain.entities.transaction.Transaction;
import com.lucas.grana.domain.enums.user.UserRole;
import com.lucas.grana.domain.valueObjects.User.Email;

public class User {
    private final String userName;
    private final Email email;
    private final boolean emailConfirmed;
    private final UserRole role;
    private List<Transaction> transactions;
    private Credentials credentials;

    private List<Category> categories;

    public User(String userName, Email email, boolean emailConfirmed, UserRole role,
            Credentials credentials) {
        this.userName = userName;
        this.email = email;
        this.emailConfirmed = Objects.requireNonNull(emailConfirmed, "EmailConfirmed não pode ser nulo");
        this.role = Objects.requireNonNull(role, "Role não pode ser nulo");
        this.credentials = Objects.requireNonNull(credentials, "Credentials não pode ser nulo");
    }

    public String getUserName() {
        return userName;
    }

    public Email getEmail() {
        return email;
    }

    public boolean getEmailConfirmed() {
        return emailConfirmed;
    }

    public UserRole getRole() {
        return role;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Credentials getCredentials() {
        return credentials;
    }
}
