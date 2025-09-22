package com.lucas.grana.domain.repositories;

import java.util.Optional;

import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.entities.AuthToken;

public interface AuthTokenRepository {
    Optional<AuthToken> findByUser(User user);
}
