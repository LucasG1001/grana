package com.lucas.grana.infrastructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {
    @Column(nullable = false, length = 50)
    private String categoryName;

    @Column(nullable = false, length = 20)
    private String color;

    @Column(nullable = false, length = 30)
    private String icon;
}
