package com.lucas.grana.application.service;

import java.util.List;

import com.lucas.grana.domain.Category;

public interface CategoryService {
    Category createTransaction(Category category);
    List<Category> getAllCategories();
    List<Category> findByUserId(String userId);
    
}
