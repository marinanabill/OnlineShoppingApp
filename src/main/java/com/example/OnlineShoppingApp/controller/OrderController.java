package com.example.OnlineShoppingApp.controller;

import com.example.OnlineShoppingApp.model.Order;
import com.example.OnlineShoppingApp.model.User;
import com.example.OnlineShoppingApp.service.OrderService;
import com.example.OnlineShoppingApp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService){
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("/place/{userId}")
    public Order placeOrder(@PathVariable Long userId){
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return orderService.placeOrder(user);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrders(@PathVariable Long userId){
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return orderService.getOrdersByUser(user);
    }
}
