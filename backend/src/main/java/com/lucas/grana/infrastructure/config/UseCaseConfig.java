package com.lucas.grana.infrastructure.config;

import com.lucas.grana.application.mappers.UserMapper;
import com.lucas.grana.application.security.AuthService;
import com.lucas.grana.application.security.PasswordEncoder;
import com.lucas.grana.application.security.TokenProvider;
import com.lucas.grana.application.usecases.user.CreateUserUseCase;
import com.lucas.grana.application.usecases.user.CreateUserUseCaseImpl;
import com.lucas.grana.application.usecases.user.LoginUseCase;
import com.lucas.grana.application.usecases.user.LoginUseCaseImpl;
import com.lucas.grana.domain.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final TokenProvider tokenProvider;
    private final AuthService authService;

    public UseCaseConfig(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, TokenProvider tokenProvider, AuthService authService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.tokenProvider = tokenProvider;
        this.authService = authService;
    }

    @Bean
    public CreateUserUseCase createUserUseCase() {
        return new CreateUserUseCaseImpl(userRepository, passwordEncoder, userMapper, tokenProvider);
    }

    @Bean
    public LoginUseCase loginUseCase() {
        return new LoginUseCaseImpl(userRepository, passwordEncoder, tokenProvider, authService);
    }
}
