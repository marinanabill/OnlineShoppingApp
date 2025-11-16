// OrderDTO.java
package com.example.OnlineShoppingApp.dto;
import lombok.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private Long userId;
    private List<OrderItemDTO> items;
    private Date orderDate;
    private Double totalPrice;
    private String status; // PENDING / COMPLETED / CANCELLED
}
