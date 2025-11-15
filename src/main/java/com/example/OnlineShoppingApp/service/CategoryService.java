package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category saveCategory(Category category);
    Optional<Category> getCategoryById(Long id);
    Optional<Category> getCategoryByName(String name);
    List<Category> getAllCategories();
}
