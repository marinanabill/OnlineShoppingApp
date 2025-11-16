package com.example.OnlineShoppingApp.service.impl;

import com.example.OnlineShoppingApp.model.Product;
import com.example.OnlineShoppingApp.model.Category;
import com.example.OnlineShoppingApp.repository.ProductRepository;
import com.example.OnlineShoppingApp.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Page<Product> getProducts(Pageable pageable, String search) {
        if (search == null || search.isEmpty()) {
            return productRepository.findAll(pageable);
        }
        return productRepository.findByNameContainingIgnoreCase(search, pageable);
    }

    @Override
    public Page<Product> getProducts(Pageable pageable, String search, Category category) {
        if (category != null) {
            if (search == null || search.isEmpty()) {
                return productRepository.findByCategory(category, pageable);
            }
            return productRepository.findByCategoryAndNameContainingIgnoreCase(category, search, pageable);
        } else {
            return getProducts(pageable, search);
        }
    }
}
