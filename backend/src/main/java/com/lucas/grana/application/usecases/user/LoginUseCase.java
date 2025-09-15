package com.lucas.grana.application.usecases.user;

import com.lucas.grana.application.dtos.user.LoginRequestDTO;
import com.lucas.grana.application.dtos.user.LoginResponseDTO;

public interface LoginUseCase {
    LoginResponseDTO execute(LoginRequestDTO dto);
}