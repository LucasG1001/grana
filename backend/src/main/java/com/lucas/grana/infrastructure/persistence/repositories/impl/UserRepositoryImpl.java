package com.lucas.grana.infrastructure.persistence.repositories.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.repositories.UserRepository;
import com.lucas.grana.infrastructure.persistence.entities.UserEntity;
import com.lucas.grana.infrastructure.persistence.mappers.UserMapper;
import com.lucas.grana.infrastructure.persistence.repositories.SpringDataUserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final SpringDataUserRepository jpaRepository;
    private final UserMapper userMapper;

    public UserRepositoryImpl(SpringDataUserRepository jpaRepository, UserMapper userMapper) {
        this.jpaRepository = jpaRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(userMapper::toDomain);

    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity userEntityCreated = jpaRepository.save(userEntity);
        return userMapper.toDomain(userEntityCreated);
    }

}
