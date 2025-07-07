package com.lucas.grana.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.lucas.grana.domain.user.User;

public interface UserRepository extends JpaRepository<User, String>  {

    UserDetails findByEmail(String email);
}
