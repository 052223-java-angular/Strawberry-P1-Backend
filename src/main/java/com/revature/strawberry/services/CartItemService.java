package com.revature.strawberry.services;

import org.springframework.stereotype.Service;

import com.revature.strawberry.dtos.requests.NewCartItemRequest;
import com.revature.strawberry.entities.Cart;
import com.revature.strawberry.entities.CartItem;
import com.revature.strawberry.entities.Product;
import com.revature.strawberry.repositories.CartItemRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CartItemService {
    private final CartService cartService;
    private final ProductService productService;
    private final CartItemRepository cartItemRepository;

    public CartItem add(NewCartItemRequest req) {
        Cart foundCart = cartService.findByUserId(req.getUserId());

        if (cartItemRepository.findByProductIdAndCartId(req.getProductId(), foundCart.getId()).isPresent()) {
            // update quantity
            CartItem foundCartItem = cartItemRepository.findByProductIdAndCartId(req.getProductId(), foundCart.getId())
                    .get();
            foundCartItem.setQuanity(foundCartItem.getQuanity() + req.getQuantity());
            return cartItemRepository.save(foundCartItem);
        }

        Product foundProduct = productService.findById(req.getProductId());
        CartItem newCartItem = new CartItem(req, foundProduct, foundCart);
        return cartItemRepository.save(newCartItem);
    }
}