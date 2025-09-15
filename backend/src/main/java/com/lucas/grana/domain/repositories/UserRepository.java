package com.lucas.grana.domain.repositories;

import java.util.Optional;

import com.lucas.grana.domain.entities.User;

public interface UserRepository {
    Optional<User> findByEmail(String email);

    User save(User user);
}