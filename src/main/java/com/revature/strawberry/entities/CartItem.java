package com.revature.strawberry.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.revature.strawberry.dtos.requests.NewCartItemRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    private String id;

    @Column(name = "quanity", nullable = false)
    private int quanity;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    public CartItem(NewCartItemRequest req, Product product, Cart cart) {
        this.id = UUID.randomUUID().toString();
        this.quanity = req.getQuantity();
        this.price = req.getPrice();
        this.product = product;
        this.cart = cart;
    }
}
