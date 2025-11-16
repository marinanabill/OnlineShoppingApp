package com.example.OnlineShoppingApp.mapper;

import com.example.OnlineShoppingApp.dto.OrderItemDTO;
import com.example.OnlineShoppingApp.model.OrderItem;

public class OrderItemMapper {

    public static OrderItemDTO toDTO(OrderItem item) {
        if (item == null) return null;

        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(item.getId());
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setPrice(item.getProduct().getPrice());
        dto.setQuantity(item.getQuantity());
        dto.setSubtotal(item.getSubtotal());

        return dto;
    }
}
