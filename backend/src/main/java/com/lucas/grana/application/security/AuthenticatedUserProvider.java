package com.lucas.grana.application.security;

import com.lucas.grana.domain.entities.User;

public interface AuthenticatedUserProvider{
    User getAuthenticatedUser();
}
