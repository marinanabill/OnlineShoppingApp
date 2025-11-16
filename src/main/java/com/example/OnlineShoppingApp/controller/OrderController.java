package com.example.OnlineShoppingApp.controller;

import com.example.OnlineShoppingApp.dto.OrderDTO;
import com.example.OnlineShoppingApp.dto.UserDTO;
import com.example.OnlineShoppingApp.mapper.UserMapper;
import com.example.OnlineShoppingApp.model.Order.OrderStatus;
import com.example.OnlineShoppingApp.service.OrderService;
import com.example.OnlineShoppingApp.service.UserService;
import org.springframework.data.domain.*;
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
    public OrderDTO placeOrder(@PathVariable Long userId){
        UserDTO user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderService.placeOrder(UserMapper.toEntity(user));
    }

    @GetMapping("/user/{userId}")
    public List<OrderDTO> getOrders(@PathVariable Long userId){
        UserDTO user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderService.getOrdersByUser(UserMapper.toEntity(user));
    }

    @GetMapping("/user/{userId}/paged")
    public Page<OrderDTO> getOrdersPaged(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        UserDTO user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Pageable pageable = PageRequest.of(page, size);

        return orderService.getOrdersByUser(UserMapper.toEntity(user), pageable);
    }

    @GetMapping("/user/{userId}/paged/status")
    public Page<OrderDTO> getOrdersByStatusPaged(
            @PathVariable Long userId,
            @RequestParam OrderStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        UserDTO user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Pageable pageable = PageRequest.of(page, size);

        return orderService.getOrdersByUserAndStatus(
                UserMapper.toEntity(user), status, pageable
        );
    }
}
