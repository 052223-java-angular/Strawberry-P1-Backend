package com.revature.strawberry.entities;

import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.revature.strawberry.dtos.requests.NewProductRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "products")
public class Product {
    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "stock", nullable = false)
    private int stock;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private Set<CartItem> cartItems;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    public Product(NewProductRequest req, Category category) {
        this.id = UUID.randomUUID().toString();
        this.name = req.getName();
        this.description = req.getDescription();
        this.price = req.getPrice();
        this.image = req.getImage();
        this.stock = req.getStock();
        this.category = category;
    }
}