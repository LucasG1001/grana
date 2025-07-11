package com.lucas.grana.infra.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lucas.grana.application.dto.CreateCategoryDTO;
import com.lucas.grana.application.mapper.CategoryMapper;
import com.lucas.grana.application.service.CategoryService;
import com.lucas.grana.domain.Category;
import com.lucas.grana.infra.persistence.CategoryRepository;
import com.lucas.grana.infra.persistence.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/Categorys")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService CategoryService;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Category get(@RequestBody @Valid CreateCategoryDTO Category) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 

        String email = auth.getName();

        Category CategoryWithUser = CategoryMapper.toCategory(Category, userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found")));

        return categoryRepository.save(CategoryWithUser);
    }

    @GetMapping()
    public List<Category> getAll() {
        return CategoryService.getAllCategories();
    }

}
