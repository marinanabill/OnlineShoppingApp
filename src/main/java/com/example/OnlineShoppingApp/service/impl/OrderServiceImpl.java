package com.example.OnlineShoppingApp.service.impl;

import com.example.OnlineShoppingApp.model.*;
import com.example.OnlineShoppingApp.repository.*;
import com.example.OnlineShoppingApp.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository,
                            CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Order placeOrder(User user) {
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Cart not found"));
        List<CartItem> cartItems = cartItemRepository.findByCart(cart);

        if(cartItems.isEmpty()) throw new RuntimeException("Cart is empty");

        Order order = new Order();
        order.setUser(user);
        order.setStatus(Order.OrderStatus.PENDING);

        double totalPrice = 0.0;

        order = orderRepository.save(order);

        for(CartItem cartItem : cartItems){
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);

            totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }

        order.setTotalPrice(totalPrice);
        order.setStatus(Order.OrderStatus.COMPLETED);
        order = orderRepository.save(order);

        cartItemRepository.deleteAll(cartItems);
        return order;
    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public Page<Order> getOrdersByUser(User user, Pageable pageable) {
        return orderRepository.findByUser(user, pageable);
    }

    @Override
    public Page<Order> getOrdersByUserAndStatus(User user, Order.OrderStatus status, Pageable pageable) {
        return orderRepository.findByUserAndStatus(user, status, pageable);
    }
}
