package com.lucas.grana.application.usecases.transaction;

import com.lucas.grana.application.dtos.user.RegisterRequestDTO;
import com.lucas.grana.application.dtos.user.RegisterResponseDTO;
import com.lucas.grana.application.mappers.UserMapper;
import com.lucas.grana.application.security.PasswordHasher;
import com.lucas.grana.application.security.TokenProvider;
import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.exceptions.user.UserAlreadyExistsException;
import com.lucas.grana.domain.repositories.UserRepository;
import com.lucas.grana.domain.valueObjects.User.Email;
import com.lucas.grana.domain.valueObjects.User.Password;

public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final UserRepository userRepository;
    private final PasswordHasher passwordEncoder;
    private final UserMapper userMapper;
    private final TokenProvider tokenProvider;

    public CreateTransactionUseCaseImpl(UserRepository userRepository, PasswordHasher passwordEncoder,
            UserMapper userMapper, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public RegisterResponseDTO execute(RegisterRequestDTO dto) {
        Email email = new Email(dto.email());
        new Password(dto.password());

        var userFound = userRepository.findByEmail(email.toString());

        if (userFound.isPresent()) {
            throw new UserAlreadyExistsException(email.toString());
        }

        var passwordHash = passwordEncoder.encode(dto.password());

        var mappedUser = userMapper.toUser(dto, passwordHash);

        User savedUser = userRepository.save(mappedUser);

        String accessToken = tokenProvider.generateAccessToken(savedUser);
        String refreshToken = tokenProvider.generateRefreshToken(savedUser);

        return new RegisterResponseDTO(accessToken, refreshToken);
    }
}
