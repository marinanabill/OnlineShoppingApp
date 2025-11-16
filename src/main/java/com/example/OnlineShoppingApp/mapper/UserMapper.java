package com.example.OnlineShoppingApp.mapper;

import com.example.OnlineShoppingApp.dto.UserDTO;
import com.example.OnlineShoppingApp.model.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
