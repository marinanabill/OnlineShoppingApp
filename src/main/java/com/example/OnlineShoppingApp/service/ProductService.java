package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.model.Product;
import com.example.OnlineShoppingApp.model.Category;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(Category category);
}
