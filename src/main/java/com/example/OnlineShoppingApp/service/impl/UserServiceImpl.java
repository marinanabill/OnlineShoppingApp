package com.example.OnlineShoppingApp.service.impl;

import com.example.OnlineShoppingApp.dto.UserDTO;
import com.example.OnlineShoppingApp.mapper.UserMapper;
import com.example.OnlineShoppingApp.model.User;
import com.example.OnlineShoppingApp.repository.UserRepository;
import com.example.OnlineShoppingApp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        User saved = userRepository.save(user);
        return UserMapper.toDTO(saved);
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(UserMapper::toDTO);
    }

    @Override
    public Optional<UserDTO> getUserByUsername(String username) {
        return userRepository.findByUsername(username).map(UserMapper::toDTO);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }
}
