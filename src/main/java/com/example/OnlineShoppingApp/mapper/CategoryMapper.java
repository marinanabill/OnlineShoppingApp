package com.example.OnlineShoppingApp.mapper;

import com.example.OnlineShoppingApp.dto.CategoryDTO;
import com.example.OnlineShoppingApp.model.Category;

public class CategoryMapper {

    public static CategoryDTO toDTO(Category category) {
        if (category == null) return null;

        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setProductCount(
                category.getProducts() != null ? category.getProducts().size() : 0
        );
        return dto;
    }
}
