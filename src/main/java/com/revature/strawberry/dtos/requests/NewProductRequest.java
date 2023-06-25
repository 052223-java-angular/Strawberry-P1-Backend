package com.revature.strawberry.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewProductRequest {
    private String name;
    private String description;
    private double price;
    private String image;
    private int stock;
    private String categoryId;
}