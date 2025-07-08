package com.lucas.grana.infra.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.grana.application.dto.TokenResponseDto;
import com.lucas.grana.application.dto.User.UserLoginDto;
import com.lucas.grana.application.dto.User.UserRegisterDto;
import com.lucas.grana.domain.User;
import com.lucas.grana.infra.persistence.UserRepository;
import com.lucas.grana.infra.security.JwtTokenProvider;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody UserLoginDto userLoginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.email(),
                        userLoginDto.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtTokenProvider.generateAccessToken(authentication);
        String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

        return ResponseEntity.ok(new TokenResponseDto(accessToken, refreshToken));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRegisterDto userRegisterDto){

        var user = this.userRepository.findByEmail(userRegisterDto.email());

        if(user != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(userRegisterDto.password());
        var newUser = new User(userRegisterDto.email(), encryptedPassword, userRegisterDto.role());
        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
