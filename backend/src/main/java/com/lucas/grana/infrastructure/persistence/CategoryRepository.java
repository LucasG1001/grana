package com.lucas.grana.infra.persistence;

import org.springframework.stereotype.Repository;

import com.lucas.grana.domain.entities.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
    CategoryEntity findByName(String name);

    List<CategoryEntity> findByUserId(String userId);

    CategoryEntity findByUserIdAndNameIgnoreCase(String userId, String name);
}
