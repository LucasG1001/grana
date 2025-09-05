package com.lucas.grana.domain.entities;

import java.util.List;

import com.lucas.grana.domain.entities.credentials.Credentials;
import com.lucas.grana.domain.entities.transaction.Transaction;
import com.lucas.grana.domain.enums.user.UserRole;
import com.lucas.grana.domain.valueObjects.User.Email;

public class User {
    private final String userName;
    private final Email email;
    private final boolean emailConfirmed;
    private final String passwordHash;
    private final UserRole role;
    private List<Transaction> transactions;
    private Credentials credentials;

    private List<Category> categories;

    public User(String userName, Email email, boolean emailConfirmed, String passwordHash, UserRole role,
            Credentials credentials) {
        this.userName = userName;
        this.email = email;
        this.emailConfirmed = emailConfirmed;
        this.passwordHash = passwordHash;
        this.role = role;
        this.credentials = credentials;
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

    public String getPassword() {
        return passwordHash;
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
