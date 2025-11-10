package com.example.OnlineShoppingApp.service.impl;

import com.example.OnlineShoppingApp.payload.LoginRequest;
import com.example.OnlineShoppingApp.payload.RegisterRequest;
import com.example.OnlineShoppingApp.model.Role;
import com.example.OnlineShoppingApp.model.User;
import com.example.OnlineShoppingApp.repository.RoleRepository;
import com.example.OnlineShoppingApp.repository.UserRepository;
import com.example.OnlineShoppingApp.security.JwtUtil;
import com.example.OnlineShoppingApp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtUtil jwtUtil;

    @Override
    public String register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername() != null ? request.getUsername() : request.getEmail());
        user.setFullName(request.getFullName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));

        user.setRoles(new HashSet<>() {{ add(userRole); }});
        userRepository.save(user);

        return "Registered";
    }

    @Override
    public String login(LoginRequest request) {
        // authenticate (throws if invalid)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // generate token
        return jwtUtil.generateToken(request.getEmail());
    }
}
