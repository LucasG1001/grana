package com.lucas.grana.application.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lucas.grana.application.service.CategoryService;
import com.lucas.grana.domain.Category;
import com.lucas.grana.infra.persistence.CategoryRepository;
import com.lucas.grana.infra.persistence.UserRepository;
import com.lucas.grana.infra.user.AuthenticatedUserProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository CategoryRepository;
    private final AuthenticatedUserProvider userProvider;

    @Override
    public Category save(Category category){
        return CategoryRepository.save(category);
    }
    @Override
    public List<Category> findByUserId(String userId) {
        return CategoryRepository.findByUserId(userId);
    }

    @Override
    public Category update(Category category) {
        return CategoryRepository.save(category);
    }


    @Override
    public void deleteById(String id) {
        CategoryRepository.deleteById(id);
    }
    @Override
    public Optional<Category> findById(String id) {
        return CategoryRepository.findById(id);
    }
    @Override
    public Category getCategoryByUserIdAndCategoryName(String userId, String categoryName) {
        return CategoryRepository.findByUserId(userId).stream().filter(c -> c.getName().equalsIgnoreCase(categoryName)).findFirst().orElse(null);
    }
}
