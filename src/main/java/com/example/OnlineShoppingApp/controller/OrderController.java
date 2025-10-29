package com.example.OnlineShoppingApp.controller;

import com.example.OnlineShoppingApp.model.Order;
import com.example.OnlineShoppingApp.model.OrderItem;
import com.example.OnlineShoppingApp.model.User;
import com.example.OnlineShoppingApp.service.OrderService;
import com.example.OnlineShoppingApp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long userId, @RequestBody List<OrderItem> items) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(orderService.createOrder(user, items));
    }
}
