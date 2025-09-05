package com.lucas.grana.application.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lucas.grana.application.dto.CategoryResponseDTO;
import com.lucas.grana.application.dto.CreateCategoryDTO;
import com.lucas.grana.application.dto.UpdateCategoryDTO;
import com.lucas.grana.application.service.CategoryService;
import com.lucas.grana.domain.entities.Category;
import com.lucas.grana.domain.entities.User;
import com.lucas.grana.infra.persistence.CategoryRepository;
import com.lucas.grana.infra.user.AuthenticatedUserProvider;
import com.lucas.grana.infrastructure.mapper.CategoryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final AuthenticatedUserProvider userProvider;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDTO createCategory(CreateCategoryDTO dto) {
        User user = userProvider.getAuthenticatedUser();

        CategoryEntity category = categoryMapper.fromCreate(dto, user);

        CategoryEntity savedCategory = categoryRepository.save(category);

        return categoryMapper.toCategoryResponse(savedCategory);
    }

    @Override
    public CategoryResponseDTO findById(String id) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public CategoryEntity findCategoryEntityById(String id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    @Override
    public CategoryResponseDTO save(CreateCategoryDTO dto) {
        CategoryEntity category = categoryMapper.fromCreate(dto, userProvider.getAuthenticatedUser());
        CategoryEntity savedCategory = categoryRepository.save(category);

        return categoryMapper.toCategoryResponse(savedCategory);
    }

    @Override
    public CategoryResponseDTO update(String id, UpdateCategoryDTO category) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean deleteById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public List<CategoryResponseDTO> findByAuthenticatedUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByAuthenticatedUser'");
    }
}
