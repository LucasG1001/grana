package com.lucas.grana.domain.repositories;

import java.util.Optional;

import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.entities.VerificationCode;

public interface VerificationCodeRepository {
    Optional<VerificationCode> findByUser(User user);
}
