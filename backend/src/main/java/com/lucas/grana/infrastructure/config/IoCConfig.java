package com.lucas.grana.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lucas.grana.application.mappers.UserMapper;
import com.lucas.grana.application.mappers.impl.UserMapperImpl;
import com.lucas.grana.application.security.TokenProvider;
import com.lucas.grana.infrastructure.security.JwtTokenProvider;

@Configuration
public class IoCConfig {

    @Bean
    public UserMapper userMapper() {
        return new UserMapperImpl();
    }

    @Bean
    public TokenProvider tokenProvider() {
        return new JwtTokenProvider();
    }

}
