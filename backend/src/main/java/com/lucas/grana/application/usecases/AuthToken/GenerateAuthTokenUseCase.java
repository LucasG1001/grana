package com.lucas.grana.application.usecases.AuthToken;

import com.lucas.grana.application.security.AuthenticatedUserProvider;
import com.lucas.grana.application.security.TokenProvider;
import com.lucas.grana.domain.entities.User;

public interface GenerateAuthTokenUseCase {
    public void execute();
}
