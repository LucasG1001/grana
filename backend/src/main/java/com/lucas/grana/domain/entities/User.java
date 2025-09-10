package com.lucas.grana.domain.entities;
import java.util.Objects;
import com.lucas.grana.domain.enums.user.UserRole;
import com.lucas.grana.domain.valueObjects.User.Email;

public class User {
    private final String userName;
    private final Email email;
    private final boolean emailConfirmed;
    private final UserRole role;

    public User(String userName, Email email, boolean emailConfirmed, UserRole role) {
        this.userName = Objects.requireNonNull(userName, "UserName não pode ser nulo");
        this.email = email;
        this.emailConfirmed = Objects.requireNonNull(emailConfirmed, "EmailConfirmed não pode ser nulo");
        this.role = Objects.requireNonNull(role, "Role não pode ser nulo");
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
}
