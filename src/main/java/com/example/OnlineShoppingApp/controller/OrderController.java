package com.example.OnlineShoppingApp.controller;

import com.example.OnlineShoppingApp.dto.OrderDTO;
import com.example.OnlineShoppingApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{userId}/create")
    public ResponseEntity<OrderDTO> createOrder(@PathVariable Long userId, @RequestBody OrderDTO dto) {
        return ResponseEntity.ok(orderService.createOrder(userId, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }
}
