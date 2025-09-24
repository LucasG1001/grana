package com.lucas.grana.infrastructure.security;

import com.lucas.grana.application.security.AuthenticatedUserProvider;
import com.lucas.grana.domain.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityAuthenticatedUserProvider implements AuthenticatedUserProvider {

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal) {
            return ((UserPrincipal) authentication.getPrincipal()).getUser();
        }

        throw new IllegalStateException("Nenhum usuário autenticado encontrado");
    }
}
