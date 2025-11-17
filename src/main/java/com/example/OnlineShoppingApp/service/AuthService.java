package com.example.OnlineShoppingApp.service;

import com.example.OnlineShoppingApp.dto.LoginRequest;
import com.example.OnlineShoppingApp.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
