package com.lucas.grana.application.service;

import java.util.List;
import java.util.Optional;

import com.lucas.grana.domain.Category;

public interface CategoryService {
    Category save(Category category);
    Category update(Category category);
    Optional<Category> findById(String id);
    void deleteById(String id);
    List<Category> findByUserId(String userId);
}
