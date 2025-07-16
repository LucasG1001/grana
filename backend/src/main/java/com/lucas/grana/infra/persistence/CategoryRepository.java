package com.lucas.grana.infra.persistence;

import org.springframework.stereotype.Repository;

import com.lucas.grana.domain.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoryRepository extends  JpaRepository<Category, String> {
    Category findByName(String name);
    List<Category> findByUserId(String userId);
}
