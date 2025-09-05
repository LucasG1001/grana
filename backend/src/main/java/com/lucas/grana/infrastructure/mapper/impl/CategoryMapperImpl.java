package com.lucas.grana.infrastructure.mapper.impl;

import java.util.List;

import com.lucas.grana.application.dto.CategoryResponseDTO;
import com.lucas.grana.application.dto.CreateCategoryDTO;
import com.lucas.grana.application.dto.UpdateCategoryDTO;
import com.lucas.grana.domain.entities.Category;
import com.lucas.grana.domain.entities.User;
import com.lucas.grana.infrastructure.mapper.CategoryMapper;

public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryEntity fromCreate(CreateCategoryDTO createCategoryDTO, User user) {
        return CategoryEntity.builder()
                .name(createCategoryDTO.getName())
                .color(createCategoryDTO.getColor())
                .icon(createCategoryDTO.getIcon())
                .user(user)
                .build();
    }

    @Override
    public CategoryEntity fromUpdate(UpdateCategoryDTO updateCategoryDTO, User user) {
        return CategoryEntity.builder()
                .name(updateCategoryDTO.getName())
                .color(updateCategoryDTO.getColor())
                .icon(updateCategoryDTO.getIcon())
                .user(user)
                .build();
    }

    @Override
    public CategoryResponseDTO toCategoryResponse(CategoryEntity category) {
        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .color(category.getColor())
                .icon(category.getIcon())
                .build();
    }

    @Override
    public List<CategoryResponseDTO> toCategoryList(List<CategoryEntity> categories) {
        return categories.stream().map(this::toCategoryResponse).toList();
    }

}
