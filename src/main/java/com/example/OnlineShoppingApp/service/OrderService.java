// OrderService.java
package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.dto.OrderDTO;
import com.example.OnlineShoppingApp.model.User;
import com.example.OnlineShoppingApp.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface OrderService {
    OrderDTO placeOrder(User user);
    List<OrderDTO> getOrdersByUser(User user);
    Page<OrderDTO> getOrdersByUser(User user, Pageable pageable);
    Page<OrderDTO> getOrdersByUserAndStatus(User user, Order.OrderStatus status, Pageable pageable);
}
