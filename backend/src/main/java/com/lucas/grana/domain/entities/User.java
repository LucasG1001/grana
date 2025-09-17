package com.lucas.grana.domain.entities;

import java.util.Objects;
import com.lucas.grana.domain.enums.user.UserRole;
import com.lucas.grana.domain.valueObjects.User.Email;
import com.lucas.grana.domain.valueObjects.User.UserName;

public class User {
    private final UserName userName;
    private final Email email;
    private final boolean emailConfirmed;
    private final UserRole role;
    private final String passwordHash;

    public User(UserName userName, Email email, boolean emailConfirmed, UserRole role, String passwordHash) {
        this.passwordHash = Objects.requireNonNull(passwordHash, "passwordHash não pode ser nulo");
        this.userName = userName;
        this.email = email;
        this.emailConfirmed = Objects.requireNonNull(emailConfirmed, "EmailConfirmed não pode ser nulo");
        this.role = Objects.requireNonNull(role, "Role não pode ser nulo");
    }

    public UserName getUserName() {
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

    public String getPasswordHash() {
        return this.passwordHash;
    }
}
