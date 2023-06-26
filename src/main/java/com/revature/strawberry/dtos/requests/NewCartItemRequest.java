package com.revature.strawberry.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewCartItemRequest {
    private double price;
    private int quantity;
    private String productId;
    private String userId;
}