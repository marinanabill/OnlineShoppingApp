package com.example.OnlineShoppingApp.repository;

import com.example.OnlineShoppingApp.model.CartItem;
import com.example.OnlineShoppingApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
}
