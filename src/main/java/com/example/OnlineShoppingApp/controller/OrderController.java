package com.example.OnlineShoppingApp.controller;

import com.example.OnlineShoppingApp.model.Order;
import com.example.OnlineShoppingApp.model.User;
import com.example.OnlineShoppingApp.model.Order.OrderStatus;
import com.example.OnlineShoppingApp.service.OrderService;
import com.example.OnlineShoppingApp.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    // Place a new order for a user
    @PostMapping("/place/{userId}")
    public Order placeOrder(@PathVariable Long userId){
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return orderService.placeOrder(user);
    }

    // Get all orders for a user
    @GetMapping("/user/{userId}")
    public List<Order> getOrders(@PathVariable Long userId){
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return orderService.getOrdersByUser(user);
    }

    // Get paginated orders for a user
    @GetMapping("/user/{userId}/paged")
    public Page<Order> getOrdersPaged(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Pageable pageable = PageRequest.of(page, size);
        return orderService.getOrdersByUser(user, pageable);
    }

    // Get paginated orders filtered by status
    @GetMapping("/user/{userId}/paged/status")
    public Page<Order> getOrdersByStatusPaged(
            @PathVariable Long userId,
            @RequestParam OrderStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Pageable pageable = PageRequest.of(page, size);
        return orderService.getOrdersByUserAndStatus(user, status, pageable);
    }
}
