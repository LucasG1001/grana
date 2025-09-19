package com.lucas.grana.infrastructure.persistence.mappers;

import com.lucas.grana.domain.entities.VerificationCode;
import com.lucas.grana.infrastructure.persistence.entities.VerificationCodeEntity;

public interface VerificationCodeMapper {

    VerificationCode toDomain(VerificationCodeEntity verificationCodeEntity);
}