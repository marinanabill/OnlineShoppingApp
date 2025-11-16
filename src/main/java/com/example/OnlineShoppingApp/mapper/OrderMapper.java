// OrderMapper.java
package com.example.OnlineShoppingApp.mapper;

import com.example.OnlineShoppingApp.dto.*;
import com.example.OnlineShoppingApp.model.*;
import java.util.stream.Collectors;

public final class OrderMapper {
    private OrderMapper(){}

    public static OrderDTO toDTO(Order order){
        if(order == null) return null;
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser() != null ? order.getUser().getId() : null);
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getStatus() != null ? order.getStatus().name() : null);
        if(order.getItems() != null){
            dto.setItems(order.getItems().stream().map(OrderItemMapper::toDTO).collect(Collectors.toList()));
        }
        return dto;
    }

    public static Order toEntity(OrderDTO dto){
        if(dto == null) return null;
        Order order = new Order();
        order.setId(dto.getId());
        order.setOrderDate(dto.getOrderDate());
        order.setTotalPrice(dto.getTotalPrice());
        if(dto.getStatus() != null){
            try {
                order.setStatus(Order.OrderStatus.valueOf(dto.getStatus()));
            } catch(Exception e){
                order.setStatus(Order.OrderStatus.PENDING);
            }
        }
        if(dto.getItems() != null){
            order.setItems(dto.getItems().stream().map(OrderItemMapper::toEntity).collect(Collectors.toList()));
        }
        return order;
    }
}
