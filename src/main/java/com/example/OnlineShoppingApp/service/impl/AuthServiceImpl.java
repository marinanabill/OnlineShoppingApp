package com.example.OnlineShoppingApp.service.impl;

import com.example.OnlineShoppingApp.dto.LoginRequest;
import com.example.OnlineShoppingApp.dto.LoginResponse;
import com.example.OnlineShoppingApp.dto.UserDTO;
import com.example.OnlineShoppingApp.security.JwtUtil;
import com.example.OnlineShoppingApp.service.AuthService;
import com.example.OnlineShoppingApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest request) {
        // Get user DTO by email
        UserDTO userDTO = userService.getUserByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check password
        if (!passwordEncoder.matches(request.getPassword(), userDTO.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Authenticate using Spring Security
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(userDTO.getEmail());
        return new LoginResponse(token);
    }
}
