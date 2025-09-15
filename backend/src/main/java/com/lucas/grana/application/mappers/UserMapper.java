package com.lucas.grana.application.mappers;

import com.lucas.grana.application.dtos.user.RegisterRequestDTO;
import com.lucas.grana.domain.entities.User;

public interface UserMapper {
    public User toUser(RegisterRequestDTO dto, String passwordHash);
}
