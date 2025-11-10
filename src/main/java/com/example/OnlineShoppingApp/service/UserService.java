package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.dto.UserDTO;
import com.example.OnlineShoppingApp.payload.RegisterRequest;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    UserDTO registerUser(RegisterRequest request);
}
