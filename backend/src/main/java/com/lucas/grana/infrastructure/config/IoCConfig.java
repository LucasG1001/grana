package com.lucas.grana.infrastructure.config;

import com.lucas.grana.application.usecases.user.CreateUserUseCase;
import com.lucas.grana.application.usecases.user.CreateUserUseCaseImpl;
import com.lucas.grana.application.usecases.user.LoginUseCase;
import com.lucas.grana.application.usecases.user.LoginUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lucas.grana.application.mappers.UserMapper;
import com.lucas.grana.application.mappers.impl.UserMapperImpl;
import com.lucas.grana.application.security.AuthService;
import com.lucas.grana.application.security.TokenProvider;
import com.lucas.grana.infrastructure.security.JwtTokenProvider;
import com.lucas.grana.infrastructure.security.impl.SpringAuthServiceImpl;

@Configuration
public class IoCConfig {

    @Bean
    public LoginUseCase loginUseCaseImpl() {
        return new LoginUseCaseImpl();
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapperImpl();
    }

    @Bean
    public TokenProvider tokenProvider() {
        return new JwtTokenProvider();
    }

    @Bean
    public AuthService authService() {
        return new SpringAuthServiceImpl();
    }

}
