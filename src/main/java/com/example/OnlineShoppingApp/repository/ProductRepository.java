package com.example.OnlineShoppingApp.repository;

import com.example.OnlineShoppingApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
