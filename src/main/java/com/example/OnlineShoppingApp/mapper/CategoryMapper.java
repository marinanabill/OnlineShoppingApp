// CategoryMapper.java
package com.example.OnlineShoppingApp.mapper;

import com.example.OnlineShoppingApp.dto.*;
import com.example.OnlineShoppingApp.model.*;
import java.util.stream.Collectors;

public final class CategoryMapper {
    private CategoryMapper(){}

    public static CategoryDTO toDTO(Category category){
        if(category == null) return null;
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        if(category.getProducts() != null){
            dto.setProducts(category.getProducts().stream().map(ProductMapper::toDTO).collect(Collectors.toList()));
        }
        return dto;
    }

    public static Category toEntity(CategoryDTO dto){
        if(dto == null) return null;
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        // avoid mapping back products here to prevent cycles; services can set them
        return category;
    }
}
