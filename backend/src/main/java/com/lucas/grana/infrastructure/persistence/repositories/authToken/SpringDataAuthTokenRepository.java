package com.lucas.grana.infrastructure.persistence.repositories.authToken;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.grana.infrastructure.persistence.entities.UserEntity;
import com.lucas.grana.infrastructure.persistence.entities.AuthTokenEntity;

public interface SpringDataAuthTokenRepository extends JpaRepository<AuthTokenEntity, Long> {
    Optional<AuthTokenEntity> findByUser(UserEntity userEntity);
}