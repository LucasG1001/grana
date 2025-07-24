package com.lucas.grana.infra.user;

import com.lucas.grana.domain.User;

public interface AuthenticatedUserProvider {
    User getAuthenticatedUser();
}
