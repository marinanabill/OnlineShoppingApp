// ProductMapper.java
package com.example.OnlineShoppingApp.mapper;

import com.example.OnlineShoppingApp.dto.*;
import com.example.OnlineShoppingApp.model.*;

public final class ProductMapper {
    private ProductMapper(){}

    public static ProductDTO toDTO(Product product){
        if(product == null) return null;
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setCategory(CategoryMapper.toDTO(product.getCategory()));
        return dto;
    }

    public static Product toEntity(ProductDTO dto){
        if(dto == null) return null;
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setCategory(CategoryMapper.toEntity(dto.getCategory()));
        return product;
    }
}
