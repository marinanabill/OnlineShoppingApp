package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.model.CartItem;
import com.example.OnlineShoppingApp.model.User;
import com.example.OnlineShoppingApp.repository.CartItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;

    public CartService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> getCartItemsByUser(User user) {
        return cartItemRepository.findByUser(user);
    }

    public CartItem addItem(User user, CartItem cartItem) {
        cartItem.setUser(user);
        return cartItemRepository.save(cartItem);
    }

    public void removeItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public void clearCart(User user) {
        List<CartItem> items = cartItemRepository.findByUser(user);
        cartItemRepository.deleteAll(items);
    }
}
