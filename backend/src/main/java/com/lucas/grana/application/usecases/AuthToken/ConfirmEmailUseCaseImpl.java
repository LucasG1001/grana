package com.lucas.grana.application.usecases.AuthToken;

import com.lucas.grana.application.security.AuthenticatedUserProvider;
import com.lucas.grana.application.security.TokenProvider;
import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.exceptions.token.InvalidAuthTokenException;
import com.lucas.grana.domain.repositories.UserRepository;

import java.util.Objects;

public class ConfirmEmailUseCaseImpl implements ConfirmEmailUseCase {

    private final TokenProvider tokenProvider;
    private final AuthenticatedUserProvider authenticatedUserProvider;
    private final UserRepository userRepository;

    public ConfirmEmailUseCaseImpl(TokenProvider tokenProvider, AuthenticatedUserProvider authenticatedUserProvider, UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticatedUserProvider = authenticatedUserProvider;
        this.userRepository = userRepository;
    }


    @Override
    public void execute(String authToken) {
        String email = tokenProvider.getUsernameFromToken(authToken);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException());

        if (!user.getEmailConfirmed()) {
            user.setEmailConfirmed(true);
            userRepository.save(user);
        }
    }
}
