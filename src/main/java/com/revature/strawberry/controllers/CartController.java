package com.revature.strawberry.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.strawberry.dtos.requests.NewCartItemRequest;
import com.revature.strawberry.services.CartItemService;
import com.revature.strawberry.services.JwtService;
import com.revature.strawberry.utils.custom_exceptions.InvalidTokenException;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private final CartItemService cartItemService;
    private final JwtService jwtService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody NewCartItemRequest req, @RequestHeader("auth-token") String token) {
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

        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemService.add(req));
    }
}