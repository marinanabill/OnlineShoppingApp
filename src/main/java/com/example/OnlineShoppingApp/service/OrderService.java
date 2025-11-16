package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.model.Order;
import com.example.OnlineShoppingApp.model.User;
import com.example.OnlineShoppingApp.model.Order.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    Order placeOrder(User user);
    List<Order> getOrdersByUser(User user);
    Page<Order> getOrdersByUser(User user, Pageable pageable);
    Page<Order> getOrdersByUserAndStatus(User user, OrderStatus status, Pageable pageable);
}
