package com.lucas.grana.infrastructure.persistence.mappers.category;

import com.lucas.grana.domain.entities.User;
import com.lucas.grana.domain.valueObjects.User.Email;
import com.lucas.grana.domain.valueObjects.User.UserName;
import com.lucas.grana.infrastructure.persistence.entities.CategoryEntity;
import com.lucas.grana.infrastructure.persistence.entities.UserEntity;

import java.util.Locale.Category;

import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toDomain(CategoryEntity entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toDomain'");
    }

    @Override
    public CategoryEntity toEntity(Category category) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toEntity'");
    }

}
