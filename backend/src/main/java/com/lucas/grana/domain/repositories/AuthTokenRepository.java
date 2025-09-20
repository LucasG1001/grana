package com.lucas.grana.domain.repositories;

import java.util.Optional;

import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.entities.Token;

public interface AuthTokenRepository {
    Optional<Token> findByUser(User user);
}
