// CategoryService.java
package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.dto.CategoryDTO;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    CategoryDTO saveCategory(CategoryDTO category);
    Optional<CategoryDTO> getCategoryById(Long id);
    Optional<CategoryDTO> getCategoryByName(String name);
    List<CategoryDTO> getAllCategories();
}
