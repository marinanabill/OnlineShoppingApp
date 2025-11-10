package com.example.OnlineShoppingApp.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public class CartItemRequest {
    @NotNull
    private Long productId;

    @Min(1)
    private int quantity;

    // getters and setters
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
