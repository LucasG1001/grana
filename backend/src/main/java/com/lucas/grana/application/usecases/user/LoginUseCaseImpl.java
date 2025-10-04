package com.lucas.grana.application.usecases.user;

import com.lucas.grana.application.dtos.user.LoginRequestDTO;
import com.lucas.grana.application.dtos.user.LoginResponseDTO;
import com.lucas.grana.application.security.AuthService;
import com.lucas.grana.application.security.TokenProvider;
import com.lucas.grana.domain.validators.BetweenLengthValidator;
import com.lucas.grana.domain.validators.NotEmptyValidator;
import com.lucas.grana.domain.valueObjects.User.Email;

public class LoginUseCaseImpl implements LoginUseCase {

    private TokenProvider tokenProvider;
    private AuthService authService;

    public LoginUseCaseImpl(
            TokenProvider tokenProvider,
            AuthService authService) {
        this.tokenProvider = tokenProvider;
        this.authService = authService;
    }

    private final NotEmptyValidator notEmptyValidator = new NotEmptyValidator("Password");
    private final BetweenLengthValidator betweenLengthValidator = new BetweenLengthValidator("Password", 3, 60);

    @Override
    public LoginResponseDTO execute(LoginRequestDTO dto) {
        Email email = new Email(dto.email());
        notEmptyValidator.validate(dto.password());
        betweenLengthValidator.validate(dto.password());

        var user = authService.authenticate(email.toString(), dto.password());

        String accessToken = tokenProvider.generateAccessToken(user);
        String refreshToken = tokenProvider.generateRefreshToken(user);

        return new LoginResponseDTO(user.getUserName().toString(), user.getEmail().toString(), accessToken, refreshToken);
    }

}