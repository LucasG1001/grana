package com.lucas.grana.infrastructure.persistence.repositories.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.repositories.UserRepository;
import com.lucas.grana.infrastructure.persistence.entities.UserEntity;
import com.lucas.grana.infrastructure.persistence.mappers.user.UserMapper;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SpringDataUserRepository jpaRepository;
    @Autowired
    private UserMapper userMapper;

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
