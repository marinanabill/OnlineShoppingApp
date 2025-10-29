package com.example.OnlineShoppingApp.controller;

import com.example.OnlineShoppingApp.model.CartItem;
import com.example.OnlineShoppingApp.model.User;
import com.example.OnlineShoppingApp.service.CartService;
import com.example.OnlineShoppingApp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(cartService.getCartItemsByUser(user));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<CartItem> addItem(@PathVariable Long userId, @RequestBody CartItem cartItem) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(cartService.addItem(user, cartItem));
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long cartItemId) {
        cartService.removeItem(cartItemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        cartService.clearCart(user);
        return ResponseEntity.noContent().build();
    }
}
