package com.example.OnlineShoppingApp.repository;

import com.example.OnlineShoppingApp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
