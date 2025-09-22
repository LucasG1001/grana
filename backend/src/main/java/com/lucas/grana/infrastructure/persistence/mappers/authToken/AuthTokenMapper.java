package com.lucas.grana.infrastructure.persistence.mappers.authToken;

import com.lucas.grana.domain.entities.AuthToken;
import com.lucas.grana.infrastructure.persistence.entities.AuthTokenEntity;

public interface AuthTokenMapper {

    AuthToken toDomain(AuthTokenEntity verificationCodeEntity);
}