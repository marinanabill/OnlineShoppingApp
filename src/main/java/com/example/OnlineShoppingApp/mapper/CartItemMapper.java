// CartItemMapper.java
package com.example.OnlineShoppingApp.mapper;

import com.example.OnlineShoppingApp.dto.*;
import com.example.OnlineShoppingApp.model.*;

public final class CartItemMapper {
    private CartItemMapper(){}

    public static CartItemDTO toDTO(CartItem item){
        if(item == null) return null;
        CartItemDTO dto = new CartItemDTO();
        dto.setId(item.getId());
        dto.setQuantity(item.getQuantity());
        dto.setProduct(ProductMapper.toDTO(item.getProduct()));
        dto.setCartId(item.getCart() != null ? item.getCart().getId() : null);
        dto.setSubtotal(item.getProduct() != null ? item.getProduct().getPrice() * item.getQuantity() : 0.0);
        return dto;
    }

    public static CartItem toEntity(CartItemDTO dto){
        if(dto == null) return null;
        CartItem item = new CartItem();
        item.setId(dto.getId());
        item.setQuantity(dto.getQuantity());
        item.setProduct(ProductMapper.toEntity(dto.getProduct()));
        // cart linking is responsibility of service
        return item;
    }
}
