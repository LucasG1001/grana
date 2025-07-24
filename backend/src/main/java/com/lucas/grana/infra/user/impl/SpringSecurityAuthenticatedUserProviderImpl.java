package com.lucas.grana.infra.user.impl;

import org.springframework.stereotype.Component;

import com.lucas.grana.domain.User;
import com.lucas.grana.infra.persistence.UserRepository;
import com.lucas.grana.infra.user.AuthenticatedUserProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SpringSecurityAuthenticatedUserProviderImpl implements AuthenticatedUserProvider {

    private final UserRepository userRepository;
    @Override
    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
    
}
