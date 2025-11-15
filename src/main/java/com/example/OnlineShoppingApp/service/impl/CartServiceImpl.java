package com.example.OnlineShoppingApp.service.impl;

import com.example.OnlineShoppingApp.model.Cart;
import com.example.OnlineShoppingApp.model.CartItem;
import com.example.OnlineShoppingApp.model.Product;
import com.example.OnlineShoppingApp.model.User;
import com.example.OnlineShoppingApp.repository.CartItemRepository;
import com.example.OnlineShoppingApp.repository.CartRepository;
import com.example.OnlineShoppingApp.repository.ProductRepository;
import com.example.OnlineShoppingApp.service.CartService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> getCartByUser(User user) {
        return cartRepository.findByUser(user);
    }

    @Override
    public CartItem addItemToCart(User user, Long productId, int quantity) {
        Cart cart = getCartByUser(user).orElse(createCart(user));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(quantity);
        return cartItemRepository.save(item);
    }

    @Override
    public List<CartItem> getCartItems(User user) {
        Cart cart = getCartByUser(user).orElse(createCart(user));
        return cartItemRepository.findByCart(cart);
    }

    @Override
    public void removeItemFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public void clearCart(User user) {
        Cart cart = getCartByUser(user).orElseThrow(() -> new RuntimeException("Cart not found"));
        cartItemRepository.deleteAll(cartItemRepository.findByCart(cart));
    }
}
