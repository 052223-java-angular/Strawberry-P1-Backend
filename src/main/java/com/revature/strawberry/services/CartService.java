package com.revature.strawberry.services;

import org.springframework.stereotype.Service;

import com.revature.strawberry.entities.Cart;
import com.revature.strawberry.entities.User;
import com.revature.strawberry.repositories.CartRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserService userService;

    private Cart createCart(String userId) {
        User foundUser = userService.findById(userId);
        Cart newCart = new Cart(foundUser);
        return cartRepository.save(newCart);
    }

    public Cart findByCartId(String cartId, String userId) {
        return cartRepository.findById(cartId).orElseGet(() -> createCart(userId));
    }

    public Cart findByUserId(String userId) {
        return cartRepository.findByUserId(userId).orElseGet(() -> createCart(userId));
    }
}