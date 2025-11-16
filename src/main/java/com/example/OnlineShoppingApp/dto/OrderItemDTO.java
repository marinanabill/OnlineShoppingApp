package com.example.OnlineShoppingApp.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;
    private Long productId;
    private String productName;
    private Double price;
    private int quantity;
    private double subtotal;
}
