package com.example.OnlineShoppingApp.repository;

import com.example.OnlineShoppingApp.model.Order;
import com.example.OnlineShoppingApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
