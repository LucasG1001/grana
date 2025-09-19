package com.lucas.grana.infrastructure.persistence.repositories.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.entities.VerificationCode;
import com.lucas.grana.domain.repositories.VerificationCodeRepository;
import com.lucas.grana.infrastructure.persistence.entities.UserEntity;
import com.lucas.grana.infrastructure.persistence.entities.VerificationCodeEntity;
import com.lucas.grana.infrastructure.persistence.mappers.UserMapper;
import com.lucas.grana.infrastructure.persistence.mappers.VerificationCodeMapper;
import com.lucas.grana.infrastructure.persistence.repositories.SpringDataVerificationCode;

public class CodeRepositoryImpl implements VerificationCodeRepository {

    @Autowired
    private SpringDataVerificationCode springDataVerificationCode;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerificationCodeMapper verificationCodeMapper;

    @Override
    public Optional<VerificationCode> findByUser(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        return springDataVerificationCode.findByUser(userEntity).map(verificationCodeMapper::toDomain);

    }

}
