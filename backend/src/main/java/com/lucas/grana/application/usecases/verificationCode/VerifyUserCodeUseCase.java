package com.lucas.grana.application.usecases.verificationCode;

import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.entities.VerificationCode;
import com.lucas.grana.domain.repositories.VerificationCodeRepository;

public class VerifyUserCodeUseCase {
    private final VerificationCodeRepository repository;

    public VerifyUserCodeUseCase(VerificationCodeRepository repository) {
        this.repository = repository;
    }

    public boolean execute(User user, String inputCode) {
        VerificationCode verificationCode = repository.findByUser(user).

    }
}
