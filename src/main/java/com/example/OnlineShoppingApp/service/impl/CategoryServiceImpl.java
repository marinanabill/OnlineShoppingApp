package com.example.OnlineShoppingApp.service.impl;

import com.example.OnlineShoppingApp.dto.CategoryDTO;
import com.example.OnlineShoppingApp.mapper.CategoryMapper;
import com.example.OnlineShoppingApp.model.Category;
import com.example.OnlineShoppingApp.repository.CategoryRepository;
import com.example.OnlineShoppingApp.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.toEntity(categoryDTO);
        Category saved = categoryRepository.save(category);
        return CategoryMapper.toDTO(saved);
    }

    @Override
    public Optional<CategoryDTO> getCategoryById(Long id) {
        return categoryRepository.findById(id).map(CategoryMapper::toDTO);
    }

    @Override
    public Optional<CategoryDTO> getCategoryByName(String name) {
        return categoryRepository.findByName(name).map(CategoryMapper::toDTO);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }
}
