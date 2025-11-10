package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.payload.LoginRequest;
import com.example.OnlineShoppingApp.payload.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest request);   // returns message or token if you prefer
    String login(LoginRequest request);         // returns JWT token
}
