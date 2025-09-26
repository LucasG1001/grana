package com.lucas.grana.application.usecases.AuthToken;

import com.lucas.grana.application.security.TokenProvider;
import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.exceptions.user.UserNotFoundException;
import com.lucas.grana.domain.repositories.UserRepository;

public class ConfirmEmailUseCaseImpl implements ConfirmEmailUseCase {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    public ConfirmEmailUseCaseImpl(TokenProvider tokenProvider, UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
    }


    @Override
    public void execute(String authToken) {
        String email = tokenProvider.getUsernameFromToken(authToken);
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        if (!user.getEmailConfirmed()) {
            user.setEmailConfirmed(true);
            userRepository.save(user);
        }
    }
}