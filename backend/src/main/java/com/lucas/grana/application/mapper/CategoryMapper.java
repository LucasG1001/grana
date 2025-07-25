package com.lucas.grana.application.mapper;

import java.util.List;

import com.lucas.grana.application.dto.CategoryResponseDTO;
import com.lucas.grana.application.dto.CreateCategoryDTO;
import com.lucas.grana.domain.Category;
import com.lucas.grana.domain.User;

public interface CategoryMapper {

    Category fromCreate(CreateCategoryDTO createCategoryDTO, User user);

    Category fromUpdate(CreateCategoryDTO createCategoryDTO, User user);

    CategoryResponseDTO toCategoryResponse(Category category);

    List<CategoryResponseDTO> toCategoryList(List<Category> categories);
}
