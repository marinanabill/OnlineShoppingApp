package com.example.OnlineShoppingApp.controller;

import com.example.OnlineShoppingApp.dto.CartDTO;
import com.example.OnlineShoppingApp.dto.CartItemDTO;
import com.example.OnlineShoppingApp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCartByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<CartItemDTO> addToCart(@PathVariable Long userId, @RequestBody CartItemDTO dto) {
        return ResponseEntity.ok(cartService.addToCart(userId, dto));
    }

    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        cartService.removeFromCart(userId, productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
