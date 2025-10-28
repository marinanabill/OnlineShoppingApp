package com.example.OnlineShoppingApp.repository;

import com.example.OnlineShoppingApp.model.CartItem;
import com.example.OnlineShoppingApp.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart(Cart cart);
    Optional<CartItem> findByCartAndProductId(Cart cart, Long productId);
}
