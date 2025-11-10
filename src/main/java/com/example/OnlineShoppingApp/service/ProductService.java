package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.dto.ProductDTO;
import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long productId, ProductDTO productDTO);
    void deleteProduct(Long productId);
    ProductDTO getProductById(Long productId);
    List<ProductDTO> getAllProducts();
    List<ProductDTO> getProductsByCategory(Long categoryId);
}
