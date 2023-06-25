package com.revature.strawberry.services;

import org.springframework.stereotype.Service;

import com.revature.strawberry.dtos.requests.NewProductRequest;
import com.revature.strawberry.entities.Category;
import com.revature.strawberry.entities.Product;
import com.revature.strawberry.repositories.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ProductService {
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    public Product createProduct(NewProductRequest req) {
        Category foundCategory = categoryService.findCategoryById(req.getCategoryId());
        Product newProduct = new Product(req, foundCategory);
        return productRepository.save(newProduct);
    }
}