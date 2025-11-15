package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.model.Cart;
import com.example.OnlineShoppingApp.model.CartItem;
import com.example.OnlineShoppingApp.model.User;
import java.util.List;
import java.util.Optional;

public interface CartService {
    Cart createCart(User user);
    Optional<Cart> getCartByUser(User user);
    CartItem addItemToCart(User user, Long productId, int quantity);
    List<CartItem> getCartItems(User user);
    void removeItemFromCart(Long cartItemId);
    void clearCart(User user);
}
