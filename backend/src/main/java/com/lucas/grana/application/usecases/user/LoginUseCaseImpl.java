package com.lucas.grana.application.usecases.user;

import com.lucas.grana.application.dtos.user.LoginRequestDTO;
import com.lucas.grana.application.dtos.user.LoginResponseDTO;
import com.lucas.grana.application.security.TokenProvider;
import com.lucas.grana.domain.repositories.UserRepository;
import com.lucas.grana.domain.validators.NotEmptyValidator;
import com.lucas.grana.domain.valueObjects.User.Email;

public class LoginUseCaseImpl implements LoginUseCase {

    public LoginUseCaseImpl(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    private final NotEmptyValidator notEmptyValidator = new NotEmptyValidator("Password");

    @Override
    public LoginResponseDTO execute(LoginRequestDTO dto) {
        Email email = new Email(dto.email());
        notEmptyValidator.validate(dto.password());

        User user = 
    }

}