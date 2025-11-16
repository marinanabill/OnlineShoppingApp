package com.example.OnlineShoppingApp.service.impl;

import com.example.OnlineShoppingApp.dto.CartItemDTO;
import com.example.OnlineShoppingApp.mapper.CartItemMapper;
import com.example.OnlineShoppingApp.mapper.UserMapper;
import com.example.OnlineShoppingApp.model.*;
import com.example.OnlineShoppingApp.repository.CartItemRepository;
import com.example.OnlineShoppingApp.repository.CartRepository;
import com.example.OnlineShoppingApp.repository.ProductRepository;
import com.example.OnlineShoppingApp.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository,
                           CartItemRepository cartItemRepository,
                           ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CartItemDTO addItemToCart(User user, Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);

        updateCartTotal(user);

        return CartItemMapper.toDTO(cartItem);
    }

    @Override
    public List<CartItemDTO> getCartItems(User user) {
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        return cartItemRepository.findByCart(cart)
                .stream()
                .map(CartItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void removeItemFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public void clearCart(User user) {
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cartItemRepository.deleteAll(cartItemRepository.findByCart(cart));
    }

    @Override
    public void updateCartTotal(User user) {
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        double total = cartItemRepository.findByCart(cart)
                .stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
                .sum();
        cart.setTotalPrice(total);
        cartRepository.save(cart);
    }

    @Override
    public Double getCartTotal(User user) {
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        return cart.getTotalPrice();
    }
}
