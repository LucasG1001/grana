package com.lucas.grana.infrastructure.persistence.user;
import java.util.Objects;
import com.lucas.grana.domain.enums.user.UserRole;
import com.lucas.grana.domain.valueObjects.User.Email;
import com.lucas.grana.infrastructure.persistence.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column(nullable = false, length = 60)
    private String userName;

    @Column(nullable =  false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private boolean emailConfirmed;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;
}
