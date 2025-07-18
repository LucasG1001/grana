package com.lucas.grana.application.mapper;

import java.util.List;

import com.lucas.grana.application.dto.CategoryResponseDTO;
import com.lucas.grana.domain.Category;

public class CategoryResponseMapper {
    public static CategoryResponseDTO toCategoryResponse(Category category) {
        return CategoryResponseDTO.builder()
            .id(category.getId())
            .name(category.getName())
            .color(category.getColor())
            .icon(category.getIcon())
            .build();
    }

    public static List<CategoryResponseDTO> toCategoryList(List<Category> categories) {
        return categories.stream().map(CategoryResponseMapper::toCategoryResponse).toList();
    }
}

