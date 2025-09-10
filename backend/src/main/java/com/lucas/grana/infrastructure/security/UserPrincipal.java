package com.lucas.grana.infrastructure.security;


import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.enums.user.UserRole;
import com.lucas.grana.infrastructure.persistence.user.UserEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPrincipal implements UserDetails {

    private final UserEntity user;

    public UserPrincipal(UserEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.user.getRole().equals(UserRole.ADMIN)) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        return java.util.Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }
}
