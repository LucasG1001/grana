package com.lucas.grana.infrastructure.persistence.mappers.impl;

import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.valueObjects.User.Email;
import com.lucas.grana.infrastructure.persistence.entities.UserEntity;
import com.lucas.grana.infrastructure.persistence.mappers.UserMapper;

public class UserMapperImpl implements UserMapper {

    @Override
    public User toDomain(UserEntity entity) {
        return new User(entity.getUserName(), new Email(entity.getEmail()), false, entity.getRole(),
                entity.getPasswordHash());
    }

    @Override
    public UserEntity toEntity(User user) {
        return new UserEntity(user.getUserName(), user.getEmail().toString(), user.getEmailConfirmed(), user.getRole(),
                user.getPasswordHash());
    }

}
