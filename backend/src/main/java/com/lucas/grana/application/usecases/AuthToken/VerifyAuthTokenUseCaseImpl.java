package com.lucas.grana.application.usecases.AuthToken;

import java.time.LocalDateTime;
import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.exceptions.token.ExpiredAuthTokenException;
import com.lucas.grana.domain.exceptions.token.InvalidAuthTokenException;
import com.lucas.grana.domain.exceptions.token.AuthTokenNotGeneratedException;
import com.lucas.grana.domain.repositories.AuthTokenRepository;
import com.lucas.grana.domain.entities.Token;
import com.lucas.grana.domain.valueObjects.security.TokenValue;

public class VerifyAuthTokenUseCaseImpl implements VerifyAuthTokenUseCase {
    private final AuthTokenRepository repository;

    public VerifyAuthTokenUseCaseImpl(AuthTokenRepository repository) {
        this.repository = repository;
    }

    public void execute(User user, String inputCode) {

        new TokenValue(inputCode);

        Token token = repository.findByUser(user)
                .orElseThrow(() -> new AuthTokenNotGeneratedException(user.getEmail().toString()));

        validateExpiration(token);
        validateCode(token, inputCode.toString());
    }

    private void validateExpiration(Token token) {
        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new ExpiredAuthTokenException();
        }
    }

    private void validateCode(Token token, String inputCode) {
        if (!token.getValue().toString().equals(inputCode)) {
            throw new InvalidAuthTokenException();
        }
    }
}
