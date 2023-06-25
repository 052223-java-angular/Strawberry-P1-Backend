package com.revature.strawberry.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.strawberry.dtos.requests.NewCategoryRequest;
import com.revature.strawberry.entities.Category;
import com.revature.strawberry.services.CategoryService;
import com.revature.strawberry.services.JwtService;
import com.revature.strawberry.utils.custom_exceptions.AdminPermissionException;
import com.revature.strawberry.utils.custom_exceptions.InvalidTokenException;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final JwtService jwtService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody NewCategoryRequest req,
            @RequestHeader("auth-token") String token) {
        // get token from header
        if (token == null || token.isEmpty()) {
            throw new InvalidTokenException("No token provided");
        }

        // extract user role from token
        String userRole = jwtService.extractUserRole(token);

        // validate token
        if (userRole == null || userRole.isEmpty()) {
            throw new InvalidTokenException("Invalid token");
        }

        // validate user role
        if (!userRole.equals("ADMIN")) {
            throw new AdminPermissionException("You are not authorized to create a category");
        }

        categoryService.createCategory(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findCategoryByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findCategoryById(id));
    }
}