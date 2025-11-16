// CartItemDTO.java
package com.example.OnlineShoppingApp.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private Long id;
    private Integer quantity;
    private ProductDTO product;
    private Long cartId;
    private Double subtotal; // optional convenience field
}
