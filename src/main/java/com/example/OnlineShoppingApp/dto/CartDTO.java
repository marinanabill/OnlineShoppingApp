// CartDTO.java
package com.example.OnlineShoppingApp.dto;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Long id;
    private Long userId;
    private List<CartItemDTO> items;
    private Double totalPrice;
}
