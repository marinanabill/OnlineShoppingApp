package com.example.OnlineShoppingApp.controller;

import com.example.OnlineShoppingApp.dto.CartItemDTO;
import com.example.OnlineShoppingApp.dto.UserDTO;
import com.example.OnlineShoppingApp.mapper.UserMapper;
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
    public CartItemDTO addItem(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity){
        if(quantity <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        UserDTO user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartService.addItemToCart(UserMapper.toEntity(user), productId, quantity);
    }

    @GetMapping("/{userId}")
    public List<CartItemDTO> getCartItems(@PathVariable Long userId){
        UserDTO user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartService.getCartItems(UserMapper.toEntity(user));
    }

    @DeleteMapping("/remove/{cartItemId}")
    public void removeItem(@PathVariable Long cartItemId, @RequestParam Long userId){
        UserDTO user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        cartService.removeItemFromCart(cartItemId);
        cartService.updateCartTotal(UserMapper.toEntity(user));
    }

    @DeleteMapping("/clear/{userId}")
    public void clearCart(@PathVariable Long userId){
        UserDTO user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        cartService.clearCart(UserMapper.toEntity(user));
        cartService.updateCartTotal(UserMapper.toEntity(user));
    }

    @GetMapping("/total/{userId}")
    public Double getCartTotal(@PathVariable Long userId){
        UserDTO user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartService.getCartTotal(UserMapper.toEntity(user));
    }
}
