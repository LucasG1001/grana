package com.lucas.grana.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lucas.grana.application.mappers.UserMapper;
import com.lucas.grana.application.mappers.impl.UserMapperImpl;

@Configuration
public class IoCConfig {

    @Bean
    public UserMapper userMapper() {
        return new UserMapperImpl();
    }

}
