package com.lucas.grana.infrastructure.persistence.repositories.impl;

import java.util.Optional;

import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.entities.VerificationCode;
import com.lucas.grana.domain.repositories.VerificationCodeRepository;

public class CodeRepositoryImpl implements VerificationCodeRepository {

    @Override
    public Optional<VerificationCode> findByUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUser'");
    }

}
