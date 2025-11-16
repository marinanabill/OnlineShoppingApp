package com.example.OnlineShoppingApp.mapper;

import com.example.OnlineShoppingApp.dto.ProductDTO;
import com.example.OnlineShoppingApp.model.Product;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        if (product == null) return null;

        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setCategoryId(
                product.getCategory() != null ? product.getCategory().getId() : null
        );
        return dto;
    }
}
