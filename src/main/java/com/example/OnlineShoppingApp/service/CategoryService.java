package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.dto.CategoryDTO;
import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO);
    void deleteCategory(Long categoryId);
    CategoryDTO getCategoryById(Long categoryId);
    List<CategoryDTO> getAllCategories();
}
