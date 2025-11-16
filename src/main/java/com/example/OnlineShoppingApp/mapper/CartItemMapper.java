package com.example.OnlineShoppingApp.mapper;

import com.example.OnlineShoppingApp.dto.CartItemDTO;
import com.example.OnlineShoppingApp.model.CartItem;

public class CartItemMapper {

    public static CartItemDTO toDTO(CartItem item) {
        if (item == null) return null;

        CartItemDTO dto = new CartItemDTO();
        dto.setId(item.getId());
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setPrice(item.getProduct().getPrice());
        dto.setQuantity(item.getQuantity());
        dto.setSubtotal(item.getSubtotal());

        return dto;
    }
}
