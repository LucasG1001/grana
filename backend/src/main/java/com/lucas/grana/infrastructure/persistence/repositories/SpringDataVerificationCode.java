package com.lucas.grana.infrastructure.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.grana.infrastructure.persistence.entities.UserEntity;
import com.lucas.grana.infrastructure.persistence.entities.VerificationCodeEntity;

public interface SpringDataVerificationCode extends JpaRepository<VerificationCodeEntity, Long> {
    Optional<VerificationCodeEntity> findByUser(UserEntity userEntity);
}