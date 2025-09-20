package com.lucas.grana.infrastructure.persistence.mappers.authToken;

import com.lucas.grana.domain.entities.Token;
import com.lucas.grana.infrastructure.persistence.entities.AuthTokenEntity;

public interface AuthTokenMapper {

    Token toDomain(AuthTokenEntity verificationCodeEntity);
}