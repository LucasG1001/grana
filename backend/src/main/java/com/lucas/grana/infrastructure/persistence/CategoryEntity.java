package com.lucas.grana.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucas.grana.domain.entities.UserEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(length = 7)
    private String color;

    @Column(length = 30)
    private String icon;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private java.util.List<TransactionEntity> transactions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserEntity user;
}
