package com.lucas.grana.api.controllers;

import com.lucas.grana.application.usecases.AuthToken.GenerateAuthTokenUseCase;
import com.lucas.grana.application.usecases.AuthToken.GenerateAuthTokenUseCaseImpl;
import com.lucas.grana.domain.entities.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.grana.application.dtos.user.LoginRequestDTO;
import com.lucas.grana.application.dtos.user.LoginResponseDTO;
import com.lucas.grana.application.dtos.user.RegisterRequestDTO;
import com.lucas.grana.application.dtos.user.RegisterResponseDTO;
import com.lucas.grana.application.usecases.user.CreateUserUseCase;
import com.lucas.grana.application.usecases.user.LoginUseCase;
import com.lucas.grana.infrastructure.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CreateUserUseCase createUserCase;

    @Autowired
    private GenerateAuthTokenUseCase generateAuthTokenUseCase;

    @Autowired
    private LoginUseCase loginUseCase;

    @GetMapping("/open")
    public String open() {
        return "This route is open";
    }

    @GetMapping("/validate-token")
    public boolean validateToken() {
        return true;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginDto) {
        return ResponseEntity.ok(loginUseCase.execute(loginDto));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO dto) {
        return ResponseEntity.ok(createUserCase.execute(dto));
    }

    @PostMapping("/auth/confirm-email")
    public ResponseEntity ConfirmEmail(String authToken) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/auth/confirmation-token")
    public void generateConfirmationToken() {
         generateAuthTokenUseCase.execute();
    }

}
