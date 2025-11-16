package com.example.OnlineShoppingApp.repository;

import com.example.OnlineShoppingApp.model.Product;
import com.example.OnlineShoppingApp.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Non-paginated
    List<Product> findByCategory(Category category);

    // Paginated
    Page<Product> findByCategory(Category category, Pageable pageable);

    Page<Product> findByCategoryAndNameContainingIgnoreCase(Category category, String name, Pageable pageable);

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
