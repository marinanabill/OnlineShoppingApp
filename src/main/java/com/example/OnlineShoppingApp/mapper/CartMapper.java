// CartMapper.java
package com.example.OnlineShoppingApp.mapper;

import com.example.OnlineShoppingApp.dto.*;
import com.example.OnlineShoppingApp.model.*;
import java.util.stream.Collectors;

public final class CartMapper {
    private CartMapper(){}

    public static CartDTO toDTO(Cart cart){
        if(cart == null) return null;
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setUserId(cart.getUser() != null ? cart.getUser().getId() : null);
        dto.setTotalPrice(cart.getTotalPrice());
        if(cart.getItems() != null){
            dto.setItems(cart.getItems().stream().map(CartItemMapper::toDTO).collect(Collectors.toList()));
        }
        return dto;
    }

    public static Cart toEntity(CartDTO dto){
        if(dto == null) return null;
        Cart cart = new Cart();
        cart.setId(dto.getId());
        cart.setTotalPrice(dto.getTotalPrice());
        // do not set user or items here; service should attach user and build items
        return cart;
    }
}
