package com.lucas.grana.infrastructure.persistence.mappers.category;

import java.util.Locale.Category;

import com.lucas.grana.infrastructure.persistence.entities.CategoryEntity;

public interface CategoryMapper {
    Category toDomain(CategoryEntity entity);

    CategoryEntity toEntity(Category category);
}