package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.model.CartItem;
import com.example.OnlineShoppingApp.model.User;

import java.util.List;

public interface CartService {
    CartItem addItemToCart(User user, Long productId, int quantity);
    List<CartItem> getCartItems(User user);
    void removeItemFromCart(Long cartItemId);
    void clearCart(User user);

    // Enhanced methods for total calculation and updates
    void updateCartTotal(User user);
    Double getCartTotal(User user);
}
