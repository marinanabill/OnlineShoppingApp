// UserService.java
package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.dto.UserDTO;
import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO saveUser(UserDTO user);
    Optional<UserDTO> getUserById(Long id);
    Optional<UserDTO> getUserByUsername(String username);
    List<UserDTO> getAllUsers();
}
