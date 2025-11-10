package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.dto.CartDTO;
import com.example.OnlineShoppingApp.dto.CartItemDTO;
import java.util.List;

public interface CartService {
    CartDTO getCartByUserId(Long userId);
    CartItemDTO addToCart(Long userId, CartItemDTO cartItemDTO);
    void removeFromCart(Long userId, Long productId);
    void clearCart(Long userId);
}
