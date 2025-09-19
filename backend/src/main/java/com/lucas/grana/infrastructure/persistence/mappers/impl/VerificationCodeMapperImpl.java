package com.lucas.grana.infrastructure.persistence.mappers.impl;

import com.lucas.grana.domain.entities.VerificationCode;
import com.lucas.grana.domain.valueObjects.security.Code;
import com.lucas.grana.infrastructure.persistence.entities.VerificationCodeEntity;
import com.lucas.grana.infrastructure.persistence.mappers.VerificationCodeMapper;

public class VerificationCodeMapperImpl implements VerificationCodeMapper {

    @Override
    public VerificationCode toDomain(VerificationCodeEntity entity) {
        return new VerificationCode(new Code(entity.getCode()), entity.getExpiresAt());
    }

}
