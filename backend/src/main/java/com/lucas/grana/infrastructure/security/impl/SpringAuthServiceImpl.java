package com.lucas.grana.infrastructure.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.lucas.grana.application.security.AuthService;
import com.lucas.grana.domain.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringAuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public User authenticate(String email, String password) {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(auth);

        return (User) auth.getPrincipal();
    }

}
