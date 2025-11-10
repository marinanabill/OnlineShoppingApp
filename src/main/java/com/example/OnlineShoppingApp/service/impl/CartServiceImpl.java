package com.example.OnlineShoppingApp.service.impl;

import com.example.OnlineShoppingApp.dto.CartDTO;
import com.example.OnlineShoppingApp.dto.CartItemDTO;
import com.example.OnlineShoppingApp.model.Cart;
import com.example.OnlineShoppingApp.model.CartItem;
import com.example.OnlineShoppingApp.model.Product;
import com.example.OnlineShoppingApp.model.User;
import com.example.OnlineShoppingApp.repository.CartItemRepository;
import com.example.OnlineShoppingApp.repository.CartRepository;
import com.example.OnlineShoppingApp.repository.ProductRepository;
import com.example.OnlineShoppingApp.repository.UserRepository;
import com.example.OnlineShoppingApp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    private CartDTO mapToDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setUserId(cart.getUser().getId());

        List<CartItemDTO> items = new ArrayList<>();
        if (cart.getItems() != null) {
            for (CartItem item : cart.getItems()) {
                CartItemDTO itemDTO = new CartItemDTO();
                itemDTO.setId(item.getId());
                itemDTO.setProductId(item.getProduct().getId());
                itemDTO.setQuantity(item.getQuantity());
                items.add(itemDTO);
            }
        }
        dto.setItems(items);
        return dto;
    }

    @Override
    public CartDTO getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        return mapToDTO(cart);
    }

    @Override
    public CartItemDTO addToCart(Long userId, CartItemDTO cartItemDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            newCart.setItems(new ArrayList<>());
            return cartRepository.save(newCart);
        });

        Product product = productRepository.findById(cartItemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemDTO.getQuantity());

        cart.getItems().add(cartItem);
        cartRepository.save(cart);
        return cartItemDTO;
    }

    @Override
    public void removeFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        CartItem itemToRemove = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not in cart"));
        cart.getItems().remove(itemToRemove);
        cartItemRepository.delete(itemToRemove);
        cartRepository.save(cart);
    }

    @Override
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}
