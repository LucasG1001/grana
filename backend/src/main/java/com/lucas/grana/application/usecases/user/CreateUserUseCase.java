package com.lucas.grana.application.usecases.user;

import com.lucas.grana.application.dtos.user.RegisterRequestDTO;
import com.lucas.grana.application.dtos.user.RegisterResponseDTO;

public interface CreateUserUseCase {

    public RegisterResponseDTO execute(RegisterRequestDTO dto);
}
