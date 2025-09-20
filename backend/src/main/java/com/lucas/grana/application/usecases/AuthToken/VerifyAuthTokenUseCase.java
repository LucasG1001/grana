package com.lucas.grana.application.usecases.AuthToken;

import com.lucas.grana.domain.entities.User;

public interface VerifyAuthTokenUseCase {
    public void execute(User user, String inputCode);
}
