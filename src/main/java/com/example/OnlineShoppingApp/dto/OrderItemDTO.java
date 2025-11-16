// OrderItemDTO.java
package com.example.OnlineShoppingApp.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private Long id;
    private Integer quantity;
    private ProductDTO product;
    private Long orderId;
    private Double subtotal;
}
