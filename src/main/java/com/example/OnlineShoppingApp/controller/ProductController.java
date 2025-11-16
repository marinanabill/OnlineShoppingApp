package com.example.OnlineShoppingApp.controller;

import com.example.OnlineShoppingApp.dto.ProductDTO;
import com.example.OnlineShoppingApp.dto.CategoryDTO;
import com.example.OnlineShoppingApp.mapper.CategoryMapper;
import com.example.OnlineShoppingApp.service.ProductService;
import com.example.OnlineShoppingApp.service.CategoryService;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService){
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){
        return productService.saveProduct(productDTO);
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @GetMapping("/all")
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getProductsByCategory(@PathVariable Long categoryId){
        CategoryDTO category = categoryService.getCategoryById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return productService.getProductsByCategory(
                CategoryMapper.toEntity(category)
        );
    }

    @GetMapping
    public Page<ProductDTO> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long categoryId
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        if(categoryId != null){
            CategoryDTO category = categoryService.getCategoryById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            return productService.getProducts(pageable, search, CategoryMapper.toEntity(category));
        }

        return productService.getProducts(pageable, search);
    }
}
