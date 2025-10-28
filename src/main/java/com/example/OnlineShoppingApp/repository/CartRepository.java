package com.example.OnlineShoppingApp.repository;

import com.example.OnlineShoppingApp.model.Cart;
import com.example.OnlineShoppingApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
