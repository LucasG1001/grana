package com.lucas.grana.application.usecases.transaction;

import com.lucas.grana.application.dtos.user.RegisterRequestDTO;
import com.lucas.grana.application.dtos.user.RegisterResponseDTO;

public interface CreateTransactionUseCase {

    public RegisterResponseDTO execute(RegisterRequestDTO dto);
}
