package com.example.OnlineShoppingApp.service.impl;

import com.example.OnlineShoppingApp.dto.ProductDTO;
import com.example.OnlineShoppingApp.mapper.ProductMapper;
import com.example.OnlineShoppingApp.model.Category;
import com.example.OnlineShoppingApp.model.Product;
import com.example.OnlineShoppingApp.repository.ProductRepository;
import com.example.OnlineShoppingApp.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = ProductMapper.toEntity(productDTO);
        Product saved = productRepository.save(product);
        return ProductMapper.toDTO(saved);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category)
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductDTO> getProducts(Pageable pageable, String search) {
        Page<Product> page = (search == null || search.isEmpty()) ?
                productRepository.findAll(pageable) :
                productRepository.findByNameContainingIgnoreCase(search, pageable);
        return page.map(ProductMapper::toDTO);
    }

    @Override
    public Page<ProductDTO> getProducts(Pageable pageable, String search, Category category) {
        Page<Product> page;

        if(category != null){
            page = (search == null || search.isEmpty()) ?
                    productRepository.findByCategory(category, pageable) :
                    productRepository.findByCategoryAndNameContainingIgnoreCase(category, search, pageable);
        } else {
            page = (search == null || search.isEmpty()) ?
                    productRepository.findAll(pageable) :
                    productRepository.findByNameContainingIgnoreCase(search, pageable);
        }

        return page.map(ProductMapper::toDTO);
    }
}
