package com.lucas.grana.application.usecases.AuthToken;

import com.lucas.grana.application.security.AuthenticatedUserProvider;
import com.lucas.grana.application.security.TokenProvider;
import com.lucas.grana.domain.entities.User;

public class GenerateAuthTokenUseCaseImpl implements GenerateAuthTokenUseCase{

    private final TokenProvider tokenProvider;
    private final AuthenticatedUserProvider authenticatedUser;

    public GenerateAuthTokenUseCaseImpl(TokenProvider tokenProvider, AuthenticatedUserProvider authenticatedUser) {
        this.tokenProvider = tokenProvider;
        this.authenticatedUser = authenticatedUser;
    }

    public String execute(){

        User user = authenticatedUser.getAuthenticatedUser();
        return tokenProvider.generateConfirmationToken(user);
    }
}
