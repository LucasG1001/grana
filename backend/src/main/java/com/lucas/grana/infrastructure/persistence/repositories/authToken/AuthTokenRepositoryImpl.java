package com.lucas.grana.infrastructure.persistence.repositories.authToken;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.entities.AuthToken;
import com.lucas.grana.domain.repositories.AuthTokenRepository;
import com.lucas.grana.infrastructure.persistence.entities.UserEntity;
import com.lucas.grana.infrastructure.persistence.mappers.authToken.AuthTokenMapper;
import com.lucas.grana.infrastructure.persistence.mappers.user.UserMapper;

public class AuthTokenRepositoryImpl implements AuthTokenRepository {

    @Autowired
    private SpringDataAuthTokenRepository springDataVerificationCode;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthTokenMapper verificationCodeMapper;

    @Override
    public Optional<AuthToken> findByUser(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        return springDataVerificationCode.findByUser(userEntity).map(verificationCodeMapper::toDomain);

    }

}
