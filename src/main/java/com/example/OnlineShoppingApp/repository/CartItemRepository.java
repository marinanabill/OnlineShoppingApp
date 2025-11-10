package com.example.OnlineShoppingApp.repository;

import com.example.OnlineShoppingApp.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
