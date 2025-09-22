package com.lucas.grana.infrastructure.persistence.mappers.authToken;

import com.lucas.grana.domain.entities.AuthToken;
import com.lucas.grana.domain.valueObjects.security.Token;
import com.lucas.grana.infrastructure.persistence.entities.AuthTokenEntity;

public class AuthTokenMapperImpl implements AuthTokenMapper {

    @Override
    public AuthToken toDomain(AuthTokenEntity entity) {
        return new AuthToken(new Token(entity.getValue()), entity.getExpiresAt());
    }

}
