package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO);
    Optional<UserDTO> getUserById(Long id);
    Optional<UserDTO> getUserByUsername(String username);
    Optional<UserDTO> getUserByEmail(String email);  // <- added
    List<UserDTO> getAllUsers();
}
