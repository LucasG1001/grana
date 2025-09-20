package com.lucas.grana.infrastructure.persistence.mappers.user;

import com.lucas.grana.domain.entities.User;
import com.lucas.grana.infrastructure.persistence.entities.UserEntity;

public interface UserMapper {
    User toDomain(UserEntity entity);

    UserEntity toEntity(User user);
}