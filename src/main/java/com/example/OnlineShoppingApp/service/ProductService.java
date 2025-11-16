package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.model.Product;
import com.example.OnlineShoppingApp.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(Category category);

    // Enhanced: Pagination and Search
    Page<Product> getProducts(Pageable pageable, String search);

    // Enhanced: Pagination, Search, and optional Category filter
    Page<Product> getProducts(Pageable pageable, String search, Category category);
}
