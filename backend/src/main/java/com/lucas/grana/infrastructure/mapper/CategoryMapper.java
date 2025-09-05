package com.lucas.grana.infrastructure.mapper;

import java.util.List;

import com.lucas.grana.application.dto.CategoryResponseDTO;
import com.lucas.grana.application.dto.CreateCategoryDTO;
import com.lucas.grana.application.dto.UpdateCategoryDTO;
import com.lucas.grana.domain.entities.Category;
import com.lucas.grana.domain.entities.User;

public interface CategoryMapper {

    CategoryEntity fromCreate(CreateCategoryDTO createCategoryDTO, User user);

    CategoryEntity fromUpdate(UpdateCategoryDTO updateCategoryDTO, User user);

    CategoryResponseDTO toCategoryResponse(CategoryEntity category);

    List<CategoryResponseDTO> toCategoryList(List<CategoryEntity> categories);
}
