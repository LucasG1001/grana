package com.lucas.grana.infrastructure.persistence.mappers.transaction;

import com.lucas.grana.domain.entities.Transaction;
import com.lucas.grana.infrastructure.persistence.entities.TransactionEntity;

public interface TransactionMapper {
    Transaction toDomain(TransactionEntity entity);

    TransactionEntity toEntity(Transaction transaction);
}