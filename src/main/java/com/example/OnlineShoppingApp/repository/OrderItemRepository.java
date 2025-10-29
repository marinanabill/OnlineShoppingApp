package com.example.OnlineShoppingApp.repository;

import com.example.OnlineShoppingApp.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
