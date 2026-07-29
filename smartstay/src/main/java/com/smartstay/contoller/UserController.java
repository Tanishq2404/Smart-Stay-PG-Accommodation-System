package com.smartstay.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.smartstay.dto.ApiResponse;
import com.smartstay.dto.AuthResponse;
import com.smartstay.dto.LoginRequest;
import com.smartstay.dto.UserResponse;
import com.smartstay.entity.User;
import com.smartstay.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register User
    @PostMapping("/register")
    public ApiResponse registerUser(@Valid @RequestBody User user) {
        return userService.registerUser(user);
    }

    // Login User
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return userService.login(request);
    }

    // Get All Users
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get User By ID
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Get User By Email
    @GetMapping("/email/{email}")
    public UserResponse getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    // Update User
    @PutMapping("/{id}")
    public ApiResponse updateUser(@PathVariable Long id,
                                  @Valid @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // Delete User
    @DeleteMapping("/{id}")
    public ApiResponse deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}