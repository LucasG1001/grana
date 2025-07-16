package com.lucas.grana.application.mapper;

import com.lucas.grana.application.dto.CreateCategoryDTO;
import com.lucas.grana.domain.Category;
import com.lucas.grana.domain.User;

public class CategoryMapper {

    public static Category toCategory(CreateCategoryDTO createCategoryDTO, User user) {
        return Category.builder()
            .name(createCategoryDTO.getName())
            .user(user)
            .color(createCategoryDTO.getColor())
            .build();
    }
    
}
