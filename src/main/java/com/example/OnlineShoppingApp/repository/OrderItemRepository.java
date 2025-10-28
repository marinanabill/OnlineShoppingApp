package com.example.OnlineShoppingApp.repository;

import com.example.OnlineShoppingApp.model.OrderItem;
import com.example.OnlineShoppingApp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);
}
