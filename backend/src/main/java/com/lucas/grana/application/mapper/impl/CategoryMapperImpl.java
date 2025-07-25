package com.lucas.grana.application.mapper.impl;

import java.util.List;

import com.lucas.grana.application.dto.CategoryResponseDTO;
import com.lucas.grana.application.dto.CreateCategoryDTO;
import com.lucas.grana.application.mapper.CategoryMapper;
import com.lucas.grana.domain.Category;
import com.lucas.grana.domain.User;

public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category fromCreate(CreateCategoryDTO createCategoryDTO, User user) {
        return Category.builder()
                .name(createCategoryDTO.getName())
                .color(createCategoryDTO.getColor())
                .icon(createCategoryDTO.getIcon())
                .user(user)
                .build();
    }

    @Override
    public Category fromUpdate(CreateCategoryDTO createCategoryDTO, User user) {
        return Category.builder()
                .id(createCategoryDTO.getId())
                .name(createCategoryDTO.getName())
                .color(createCategoryDTO.getColor())
                .icon(createCategoryDTO.getIcon())
                .user(user)
                .build();
    }

    @Override
    public CategoryResponseDTO toCategoryResponse(Category category) {
        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .color(category.getColor())
                .icon(category.getIcon())
                .build();
    }

    @Override
    public List<CategoryResponseDTO> toCategoryList(List<Category> categories) {
        return categories.stream().map(this::toCategoryResponse).toList();
    }

}
