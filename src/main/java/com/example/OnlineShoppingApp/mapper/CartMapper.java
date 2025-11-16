package com.example.OnlineShoppingApp.mapper;

import com.example.OnlineShoppingApp.dto.CartDTO;
import com.example.OnlineShoppingApp.dto.CartItemDTO;
import com.example.OnlineShoppingApp.model.Cart;

import java.util.stream.Collectors;

public class CartMapper {

    public static CartDTO toDTO(Cart cart) {
        if (cart == null) return null;

        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setUserId(cart.getUser().getId());
        dto.setTotalPrice(cart.getTotalPrice());

        if (cart.getItems() != null) {
            dto.setItems(
                    cart.getItems().stream()
                            .map(CartItemMapper::toDTO)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }
}
