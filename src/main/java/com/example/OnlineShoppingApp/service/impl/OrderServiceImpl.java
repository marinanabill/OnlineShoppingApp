package com.example.OnlineShoppingApp.service.impl;

import com.example.OnlineShoppingApp.dto.CartItemDTO;
import com.example.OnlineShoppingApp.dto.OrderDTO;
import com.example.OnlineShoppingApp.model.*;
import com.example.OnlineShoppingApp.repository.CartRepository;
import com.example.OnlineShoppingApp.repository.OrderRepository;
import com.example.OnlineShoppingApp.repository.UserRepository;
import com.example.OnlineShoppingApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    private OrderDTO mapToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setStatus(order.getStatus());
        dto.setTotal(order.getTotal());
        dto.setUserId(order.getUser().getId());
        dto.setPlacedAt(order.getPlacedAt());
        return dto;
    }

    @Override
    public OrderDTO createOrder(Long userId, OrderDTO orderDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus("PLACED");
        order.setPlacedAt(LocalDateTime.now());
        order.setItems(cart.getItems().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(cartItem.getProduct().getPrice());
            orderItem.setLineTotal(cartItem.getQuantity() * cartItem.getProduct().getPrice());
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toList()));

        double total = order.getItems().stream().mapToDouble(OrderItem::getLineTotal).sum();
        order.setTotal(total);

        Order savedOrder = orderRepository.save(order);

        // Clear cart after order
        cart.getItems().clear();
        cartRepository.save(cart);

        return mapToDTO(savedOrder);
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<OrderDTO> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
