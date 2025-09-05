package com.lucas.grana.application.gateway;

import java.util.Collection;

import com.lucas.grana.domain.entities.Category;

public interface CategoryRepositoy {
    Collection<Category> findByUserEmail(String email);
    Category save(Category category);
    Boolean doesNameExists(String name);
}
