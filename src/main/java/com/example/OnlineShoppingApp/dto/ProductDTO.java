package com.example.OnlineShoppingApp.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private int quantity;
    private Long categoryId;
}
