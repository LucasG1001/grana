package com.lucas.grana.application.usecases.category;


import com.lucas.grana.application.gateway.CategoryRepositoy;
import com.lucas.grana.domain.entities.Category;
import com.lucas.grana.domain.exceptions.CategoryAlreadyExistException;
import com.lucas.grana.domain.usecases.CreateCategoryUseCase;

public class CreateCategoryUseCaseImpl implements CreateCategoryUseCase {

    private CategoryRepositoy categoryRepositoy;

    public CreateCategoryUseCaseImpl(CategoryRepositoy categoryRepositoy) {
        super();
        this.categoryRepositoy = categoryRepositoy;
    }

    @Override
    public Category execute(Category category) {
        boolean exists = this.categoryRepositoy.doesNameExists(category.getName());

        if(exists){
            throw new CategoryAlreadyExistException(category.getName());
        }

        return this.categoryRepositoy.save(category);
    }
}
