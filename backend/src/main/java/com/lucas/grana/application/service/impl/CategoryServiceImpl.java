package com.lucas.grana.application.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lucas.grana.application.service.CategoryService;
import com.lucas.grana.domain.Category;
import com.lucas.grana.infra.persistence.CategoryRepository;
import com.lucas.grana.infra.persistence.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository CategoryRepository;
    private final UserRepository userRepository;

    @Override
    public Category createTransaction(Category category){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        category.setUser(user);
        return CategoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return CategoryRepository.findAll();
    }
    
}
