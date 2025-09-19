package com.lucas.grana.application.usecases.verificationCode;

import java.time.LocalDateTime;
import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.entities.VerificationCode;
import com.lucas.grana.domain.repositories.VerificationCodeRepository;

public class VerifyUserCodeUseCase {
    private final VerificationCodeRepository repository;

    public VerifyUserCodeUseCase(VerificationCodeRepository repository) {
        this.repository = repository;
    }

    public void execute(User user, String inputCode) {
        VerificationCode verificationCode = repository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Nenhum código gerado para " + user.getEmail()));

        validateExpiration(verificationCode);
        validateCode(verificationCode, inputCode);
    }

    private void validateExpiration(VerificationCode verificationCode) {
        if (verificationCode.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Código está expirado");
        }
    }

    private void validateCode(VerificationCode verificationCode, String inputCode) {
        if (!verificationCode.getCode(),toString().equals(inputCode)) {
            throw new IllegalArgumentException("Código invalido");
        }
    }
}
