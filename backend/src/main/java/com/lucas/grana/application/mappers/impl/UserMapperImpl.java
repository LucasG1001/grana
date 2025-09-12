package com.lucas.grana.application.mappers.impl;

import com.lucas.grana.application.dtos.RegisterRequestDTO;
import com.lucas.grana.application.mappers.UserMapper;
import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.valueObjects.User.Email;

public class UserMapperImpl implements UserMapper {
    @Override
    public User toUser(RegisterRequestDTO dto) {
        return new User(dto.userName(), new Email(dto.email()), false, dto.role());
    }
}
