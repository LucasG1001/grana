package com.lucas.grana.application.mappers;

import com.lucas.grana.application.dtos.RegisterRequestDTO;
import com.lucas.grana.domain.entities.User;

public interface UserMapper {
    User toUser(RegisterRequestDTO dto);
}
