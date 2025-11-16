// OrderItemMapper.java
package com.example.OnlineShoppingApp.mapper;

import com.example.OnlineShoppingApp.dto.*;
import com.example.OnlineShoppingApp.model.*;

public final class OrderItemMapper {
    private OrderItemMapper(){}

    public static OrderItemDTO toDTO(OrderItem item){
        if(item == null) return null;
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(item.getId());
        dto.setQuantity(item.getQuantity());
        dto.setProduct(ProductMapper.toDTO(item.getProduct()));
        dto.setOrderId(item.getOrder() != null ? item.getOrder().getId() : null);
        dto.setSubtotal(item.getProduct() != null ? item.getProduct().getPrice() * item.getQuantity() : 0.0);
        return dto;
    }

    public static OrderItem toEntity(OrderItemDTO dto){
        if(dto == null) return null;
        OrderItem item = new OrderItem();
        item.setId(dto.getId());
        item.setQuantity(dto.getQuantity());
        item.setProduct(ProductMapper.toEntity(dto.getProduct()));
        return item;
    }
}
