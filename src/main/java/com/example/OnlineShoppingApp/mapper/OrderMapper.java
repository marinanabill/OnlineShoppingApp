package com.example.OnlineShoppingApp.mapper;

import com.example.OnlineShoppingApp.dto.OrderDTO;
import com.example.OnlineShoppingApp.model.Order;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
        if (order == null) return null;

        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getStatus().name());

        if (order.getItems() != null) {
            dto.setItems(
                    order.getItems().stream()
                            .map(OrderItemMapper::toDTO)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }
}
