package com.example.OnlineShoppingApp.controller;

import com.example.OnlineShoppingApp.dto.UserDTO;
import com.example.OnlineShoppingApp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id){
        return userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }
}
