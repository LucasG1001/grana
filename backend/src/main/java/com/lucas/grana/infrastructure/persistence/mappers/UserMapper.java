package com.lucas.grana.infrastructure.persistence.mappers;

import org.springframework.stereotype.Component;

import com.lucas.grana.domain.entities.User;
import com.lucas.grana.infrastructure.persistence.entities.UserEntity;

@Component
public interface UserMapper {
    User toDomain(UserEntity entity);

    UserEntity toEntity(User user);
}