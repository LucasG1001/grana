package com.lucas.grana.infra.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

import com.lucas.grana.domain.User;
import com.lucas.grana.infra.persistence.UserRepository;

@AllArgsConstructor
@Component
public class UserAuthenticated {
    private final UserRepository userRepository;

    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
        String email = auth.getName();

        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        return user;
    }
}
