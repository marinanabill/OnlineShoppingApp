// ProductService.java
package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.dto.ProductDTO;
import com.example.OnlineShoppingApp.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductService {
    ProductDTO saveProduct(ProductDTO product);
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProducts();
    List<ProductDTO> getProductsByCategory(Category category);
    Page<ProductDTO> getProducts(Pageable pageable, String search);
    Page<ProductDTO> getProducts(Pageable pageable, String search, Category category);
}
