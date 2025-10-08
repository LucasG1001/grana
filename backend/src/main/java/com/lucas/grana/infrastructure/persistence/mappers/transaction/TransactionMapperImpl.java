package com.lucas.grana.infrastructure.persistence.mappers.transaction;

import com.lucas.grana.domain.entities.Transaction;
import com.lucas.grana.infrastructure.persistence.entities.TransactionEntity;

import org.springframework.stereotype.Component;

@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public Transaction toDomain(TransactionEntity entity) {
        throw new UnsupportedOperationException("Unimplemented method 'toDomain'");
    }

    @Override
    public TransactionEntity toEntity(Transaction transaction) {
        throw new UnsupportedOperationException("Unimplemented method 'toEntity'");
    }

}
