package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.dto.OrderDTO;
import java.util.List;

public interface OrderService {
    OrderDTO createOrder(Long userId, OrderDTO orderDTO);
    OrderDTO getOrderById(Long orderId);
    List<OrderDTO> getOrdersByUser(Long userId);
}
