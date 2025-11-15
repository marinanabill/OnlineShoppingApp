package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.model.Order;
import com.example.OnlineShoppingApp.model.User;
import java.util.List;

public interface OrderService {
    Order placeOrder(User user);
    List<Order> getOrdersByUser(User user);
}
