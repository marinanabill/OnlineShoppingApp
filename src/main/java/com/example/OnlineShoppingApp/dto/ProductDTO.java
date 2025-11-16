// ProductDTO.java
package com.example.OnlineShoppingApp.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private CategoryDTO category;
}
