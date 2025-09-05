package com.lucas.grana.domain.usecases;

import com.lucas.grana.domain.entities.Category;

public interface CreateCategoryUseCase {
    Category execute(Category category);
}
