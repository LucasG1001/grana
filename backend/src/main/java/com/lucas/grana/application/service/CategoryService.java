package com.lucas.grana.application.service;

import java.util.List;

import com.lucas.grana.application.dto.CategoryResponseDTO;
import com.lucas.grana.application.dto.CreateCategoryDTO;
import com.lucas.grana.application.dto.UpdateCategoryDTO;
import com.lucas.grana.domain.Category;

public interface CategoryService {

    CategoryResponseDTO findById(String id);

    Category findCategoryEntityById(String id);

    CategoryResponseDTO save(CreateCategoryDTO category);

    CategoryResponseDTO update(String id, UpdateCategoryDTO category);

    boolean deleteById(String id);

    List<CategoryResponseDTO> findByAuthenticatedUser();
}
