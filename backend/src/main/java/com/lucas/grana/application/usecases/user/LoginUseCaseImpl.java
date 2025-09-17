package com.lucas.grana.application.usecases.user;

import com.lucas.grana.application.dtos.user.LoginRequestDTO;
import com.lucas.grana.application.dtos.user.LoginResponseDTO;
import com.lucas.grana.application.security.AuthService;
import com.lucas.grana.application.security.PasswordEncoder;
import com.lucas.grana.application.security.TokenProvider;
import com.lucas.grana.domain.repositories.UserRepository;
import com.lucas.grana.domain.validators.NotEmptyValidator;
import com.lucas.grana.domain.valueObjects.User.Email;

public class LoginUseCaseImpl implements LoginUseCase {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private TokenProvider tokenProvider;
    private AuthService authService;

    public LoginUseCaseImpl(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            TokenProvider tokenProvider,
            AuthService authService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.authService = authService;
    }

    public LoginUseCaseImpl() {}

    private final NotEmptyValidator notEmptyValidator = new NotEmptyValidator("Password");

    @Override
    public LoginResponseDTO execute(LoginRequestDTO dto) {
        Email email = new Email(dto.email());
        notEmptyValidator.validate(dto.password());

        var user = authService.authenticate(email.toString(), dto.password());

        String accessToken = tokenProvider.generateAccessToken(user);
        String refreshToken = tokenProvider.generateRefreshToken(user);

        return new LoginResponseDTO(email.toString(), accessToken, refreshToken);
    }

}