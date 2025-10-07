package com.lucas.grana.infrastructure.persistence.mappers.user;

import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.valueObjects.User.Email;
import com.lucas.grana.domain.valueObjects.User.UserName;
import com.lucas.grana.infrastructure.persistence.entities.UserEntity;

import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements TransactionMapper {

    @Override
    public User toDomain(UserEntity entity) {
        return new User(new UserName(entity.getUserName()), new Email(entity.getEmail()), false, entity.getRole(),
                entity.getPasswordHash());
    }

    @Override
    public UserEntity toEntity(User user) {
        return new UserEntity(user.getUserName().toString(), user.getEmail().toString(), user.getEmailConfirmed(),
                user.getRole(),
                user.getPasswordHash());
    }

}
