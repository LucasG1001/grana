package com.lucas.grana.application.usecases.AuthToken;

import com.lucas.grana.application.security.AuthenticatedUserProvider;
import com.lucas.grana.application.security.TokenProvider;
import com.lucas.grana.application.services.EmailService;
import com.lucas.grana.domain.entities.User;

import java.util.HashMap;
import java.util.Map;

public class GenerateAuthTokenUseCaseImpl implements GenerateAuthTokenUseCase{

    private final TokenProvider tokenProvider;
    private final AuthenticatedUserProvider authenticatedUser;
    private final EmailService emailService;

    public GenerateAuthTokenUseCaseImpl(TokenProvider tokenProvider, AuthenticatedUserProvider authenticatedUser, EmailService emailService) {
        this.tokenProvider = tokenProvider;
        this.authenticatedUser = authenticatedUser;
        this.emailService = emailService;
    }

    public void execute(){

        User user = authenticatedUser.getAuthenticatedUser();
        String confirmationToken = tokenProvider.generateConfirmationToken(user);

        Map<String, Object> variables = new HashMap<>();
        variables.put("nome", user.getUserName());
        variables.put("confirmationLink", confirmationToken);


        emailService.sendTemplate(user.getEmail().toString(), "Obrigado por se registrar!", "confirmation-email", variables);
    }
}
