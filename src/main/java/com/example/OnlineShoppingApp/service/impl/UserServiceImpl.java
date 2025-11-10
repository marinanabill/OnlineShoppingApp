package com.example.OnlineShoppingApp.service.impl;

import com.example.OnlineShoppingApp.dto.UserDTO;
import com.example.OnlineShoppingApp.model.Role;
import com.example.OnlineShoppingApp.model.User;
import com.example.OnlineShoppingApp.payload.RegisterRequest;
import com.example.OnlineShoppingApp.repository.RoleRepository;
import com.example.OnlineShoppingApp.repository.UserRepository;
import com.example.OnlineShoppingApp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

@Override
public UserDTO createUser(UserDTO userDTO) {
    User user = new User();
    user.setUsername(userDTO.getUsername());
    user.setEmail(userDTO.getEmail());
    user.setPassword(userDTO.getPassword());

    Role userRole;
    if ("0000".equals(userDTO.getPassword())) {
        userRole = roleRepository.findByName("ROLE_ADMIN")
            .orElseThrow(() -> new RuntimeException("Admin role not found"));
    } else {
        userRole = roleRepository.findByName("ROLE_USER")
            .orElseThrow(() -> new RuntimeException("User role not found"));
    }

    user.setRoles(Set.of(userRole));
    User savedUser = userRepository.save(user);
    return mapToDTO(savedUser);
}


    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        User updatedUser = userRepository.save(user);
        return mapToDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return mapToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRoles(
            user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet())
        );
        return dto;
    }

    @Override
    public UserDTO registerUser(RegisterRequest request) {  
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(request.getUsername());
        userDTO.setEmail(request.getEmail());
        userDTO.setPassword(request.getPassword());

        // Assign roles based on password
        if ("0000".equals(request.getPassword())) {
            userDTO.setRoles(Set.of("ROLE_ADMIN"));
        } else {
            userDTO.setRoles(Set.of("ROLE_USER"));
        }

        return createUser(userDTO);
    }
}
