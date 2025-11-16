// UserMapper.java
package com.example.OnlineShoppingApp.mapper;

import com.example.OnlineShoppingApp.dto.*;
import com.example.OnlineShoppingApp.model.*;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

public final class UserMapper {
    private UserMapper(){}

    public static UserDTO toDTO(User user){
        if(user == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        // do NOT expose password in production responses — keep here for completeness
        dto.setPassword(user.getPassword());
        dto.setCart(CartMapper.toDTO(user.getCart()));
        if(user.getOrders() != null){
            dto.setOrders(user.getOrders().stream().map(OrderMapper::toDTO).collect(Collectors.toList()));
        }
        return dto;
    }

    public static User toEntity(UserDTO dto){
        if(dto == null) return null;
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        // note: do not set bidirectional links here to avoid infinite loops;
        // controllers or services should set relationships if needed
        user.setCart(CartMapper.toEntity(dto.getCart()));
        if(dto.getOrders() != null){
            user.setOrders(dto.getOrders().stream().map(OrderMapper::toEntity).collect(Collectors.toList()));
        }
        return user;
    }
}
