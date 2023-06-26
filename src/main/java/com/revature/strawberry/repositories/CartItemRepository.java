package com.revature.strawberry.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.strawberry.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Optional<CartItem> findByProductIdAndCartId(String productId, String id);
}