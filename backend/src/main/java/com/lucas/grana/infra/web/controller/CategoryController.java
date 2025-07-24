package com.lucas.grana.infra.web.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.security.core.Authentication;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lucas.grana.application.dto.CategoryResponseDTO;
import com.lucas.grana.application.dto.CreateCategoryDTO;
import com.lucas.grana.application.mapper.CategoryMapper;
import com.lucas.grana.application.mapper.CategoryResponseMapper;
import com.lucas.grana.application.service.CategoryService;
import com.lucas.grana.domain.Category;
import com.lucas.grana.infra.persistence.CategoryRepository;
import com.lucas.grana.infra.persistence.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService CategoryService;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryResponseDTO> get(@RequestBody @Valid CreateCategoryDTO Category) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 

        String email = auth.getName();

        Category CategoryWithUser = CategoryMapper.toCategory(Category, userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found")));
        CategoryResponseDTO categoryResponse = CategoryResponseMapper.toCategoryResponse(CategoryService.save(CategoryWithUser));
        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }

    @GetMapping()
    public List<CategoryResponseDTO> getByUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        var categories = CategoryService.findByUserId(user.getId());

        var categoriesResponse = CategoryResponseMapper.toCategoryList(categories);

        return  categoriesResponse;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getById(@PathVariable String id) {
        Optional<Category> category = CategoryService.findById(id);

        if(category.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(CategoryResponseMapper.toCategoryResponse(category.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable String id, @RequestBody @Valid CreateCategoryDTO Category) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
        String email = auth.getName();

        Category.setId(id);


        Category category = CategoryMapper.toCategory(Category, userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found")));
        CategoryResponseDTO categoryResponse = CategoryResponseMapper.toCategoryResponse(CategoryService.update(category));
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        CategoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
