package com.lucas.grana.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.grana.application.dtos.LoginRequestDTO;
import com.lucas.grana.application.dtos.LoginResponseDTO;
import com.lucas.grana.application.dtos.RegisterRequestDTO;
import com.lucas.grana.application.mappers.UserMapper;
import com.lucas.grana.domain.valueObjects.User.Email;
import com.lucas.grana.infrastructure.persistence.repositories.UserRepository;
import com.lucas.grana.infrastructure.security.JwtTokenProvider;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

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

        Email email = new Email(loginDto.email());

        if (loginDto.password() == null || loginDto.password().isEmpty()) {
            throw new IllegalArgumentException("Password não pode estar vaziu");
        }

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, loginDto.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtTokenProvider.generateAccessToken(authentication);
        String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

        return ResponseEntity.ok(new LoginResponseDTO(accessToken, refreshToken));
    }

    @PostMapping("register")
    public String register(@RequestBody RegisterRequestDTO dto) {
        userMapper.toUser(dto);

        return "Test";
    }
}
