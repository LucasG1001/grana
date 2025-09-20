package com.lucas.grana.infrastructure.persistence.mappers.authToken;

import com.lucas.grana.domain.entities.Token;
import com.lucas.grana.domain.valueObjects.security.TokenValue;
import com.lucas.grana.infrastructure.persistence.entities.AuthTokenEntity;

public class AuthTokenMapperImpl implements AuthTokenMapper {

    @Override
    public Token toDomain(AuthTokenEntity entity) {
        return new Token(new TokenValue(entity.getValue()), entity.getExpiresAt());
    }

}
