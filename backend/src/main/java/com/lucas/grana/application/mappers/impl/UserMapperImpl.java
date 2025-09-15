package com.lucas.grana.application.mappers.impl;

import com.lucas.grana.application.dtos.user.RegisterRequestDTO;
import com.lucas.grana.application.mappers.UserMapper;
import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.enums.user.UserRole;
import com.lucas.grana.domain.valueObjects.User.Email;

public class UserMapperImpl implements UserMapper {
    public User toUser(RegisterRequestDTO dto, String passwordHash) {
        return new User(dto.userName(), new Email(dto.email()), false, UserRole.USER, passwordHash);
    }
}
