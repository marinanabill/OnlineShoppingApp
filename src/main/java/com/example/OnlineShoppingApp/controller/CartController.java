package com.example.OnlineShoppingApp.controller;

import com.example.OnlineShoppingApp.model.CartItem;
import com.example.OnlineShoppingApp.model.User;
import com.example.OnlineShoppingApp.service.CartService;
import com.example.OnlineShoppingApp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService){
        this.cartService = cartService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public CartItem addItem(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity){
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return cartService.addItemToCart(user, productId, quantity);
    }

    @GetMapping("/{userId}")
    public List<CartItem> getCartItems(@PathVariable Long userId){
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return cartService.getCartItems(user);
    }

    @DeleteMapping("/remove/{cartItemId}")
    public void removeItem(@PathVariable Long cartItemId){
        cartService.removeItemFromCart(cartItemId);
    }

    @DeleteMapping("/clear/{userId}")
    public void clearCart(@PathVariable Long userId){
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        cartService.clearCart(user);
    }
}
