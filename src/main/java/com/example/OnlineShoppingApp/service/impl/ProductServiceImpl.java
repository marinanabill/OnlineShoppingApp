package com.example.OnlineShoppingApp.service.impl;

import com.example.OnlineShoppingApp.dto.ProductDTO;
import com.example.OnlineShoppingApp.model.Category;
import com.example.OnlineShoppingApp.model.Product;
import com.example.OnlineShoppingApp.repository.CategoryRepository;
import com.example.OnlineShoppingApp.repository.ProductRepository;
import com.example.OnlineShoppingApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private ProductDTO mapToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setCategoryId(product.getCategory().getId());
        return dto;
    }

    private Product mapToEntity(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);
        return product;
    }

    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        return mapToDTO(productRepository.save(mapToEntity(dto)));
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);
        return mapToDTO(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}
