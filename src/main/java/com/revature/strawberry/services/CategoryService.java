package com.revature.strawberry.services;

import org.springframework.stereotype.Service;

import com.revature.strawberry.dtos.requests.NewCategoryRequest;
import com.revature.strawberry.entities.Category;
import com.revature.strawberry.repositories.CategoryRepository;
import com.revature.strawberry.utils.custom_exceptions.CategoryNotFoundException;
import com.revature.strawberry.utils.custom_exceptions.ResourceConflictException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category createCategory(NewCategoryRequest req) {
        if (categoryRepository.findByName(req.getName()).isPresent()) {
            throw new ResourceConflictException("Category with name " + req.getName() + " already exists");
        }

        return categoryRepository.save(new Category(req));
    }

    public Category findCategoryById(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " does not exist"));
    }

    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException("Category with name " + name + " does not exist"));
    }
}