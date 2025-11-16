// CartService.java
package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.dto.CartItemDTO;
import com.example.OnlineShoppingApp.model.User;
import java.util.List;

public interface CartService {
    CartItemDTO addItemToCart(User user, Long productId, int quantity);
    List<CartItemDTO> getCartItems(User user);
    void removeItemFromCart(Long cartItemId);
    void clearCart(User user);
    void updateCartTotal(User user);
    Double getCartTotal(User user);
}
