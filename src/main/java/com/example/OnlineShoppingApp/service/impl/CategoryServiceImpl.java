package com.example.OnlineShoppingApp.service.impl;

import com.example.OnlineShoppingApp.dto.CategoryDTO;
import com.example.OnlineShoppingApp.model.Category;
import com.example.OnlineShoppingApp.repository.CategoryRepository;
import com.example.OnlineShoppingApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private CategoryDTO mapToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }

    private Category mapToEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO dto) {
        return mapToDTO(categoryRepository.save(mapToEntity(dto)));
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return mapToDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        return categoryRepository.findById(id).map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}
