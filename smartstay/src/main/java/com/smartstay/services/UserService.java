package com.smartstay.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartstay.dto.ApiResponse;
import com.smartstay.dto.AuthResponse;
import com.smartstay.dto.LoginRequest;
import com.smartstay.dto.UserResponse;
import com.smartstay.entity.User;
import com.smartstay.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register User
    public ApiResponse registerUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            return new ApiResponse(false, "Email already registered");
        }

        if (userRepository.existsByMobileNumber(user.getMobileNumber())) {
            return new ApiResponse(false, "Mobile number already registered");
        }

        userRepository.save(user);

        return new ApiResponse(true, "User Registered Successfully");
    }

    // Login User
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail());

        if (user == null) {
            return new AuthResponse(false, "User not found", null);
        }

        // Future: Replace with BCrypt Password Encoder
        if (!user.getPassword().equals(request.getPassword())) {
            return new AuthResponse(false, "Invalid Password", null);
        }

        return new AuthResponse(
                true,
                "Login Successful",
                mapToUserResponse(user));
    }

    // Get All Users
    public List<UserResponse> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserResponse> responseList = new ArrayList<>();

        for (User user : users) {
            responseList.add(mapToUserResponse(user));
        }

        return responseList;
    }

    // Get User By ID
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return null;
        }

        return mapToUserResponse(user);
    }

    // Get User By Email
    public UserResponse getUserByEmail(String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            return null;
        }

        return mapToUserResponse(user);
    }

    // Update User
    public ApiResponse updateUser(Long id, User updatedUser) {

        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return new ApiResponse(false, "User not found");
        }

        // Check duplicate email
        if (!user.getEmail().equals(updatedUser.getEmail())
                && userRepository.existsByEmail(updatedUser.getEmail())) {

            return new ApiResponse(false, "Email already registered");
        }

        // Check duplicate mobile number
        if (!user.getMobileNumber().equals(updatedUser.getMobileNumber())
                && userRepository.existsByMobileNumber(updatedUser.getMobileNumber())) {

            return new ApiResponse(false, "Mobile number already registered");
        }

        user.setFullName(updatedUser.getFullName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setMobileNumber(updatedUser.getMobileNumber());
        user.setGender(updatedUser.getGender());

        userRepository.save(user);

        return new ApiResponse(true, "User updated successfully");
    }

    // Delete User
    public ApiResponse deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            return new ApiResponse(false, "User not found");
        }

        userRepository.deleteById(id);

        return new ApiResponse(true, "User deleted successfully");
    }

    // Convert User Entity to UserResponse DTO
    private UserResponse mapToUserResponse(User user) {

        UserResponse response = new UserResponse();

        response.setUserId(user.getUserId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setMobileNumber(user.getMobileNumber());
        response.setGender(user.getGender());

        return response;
    }
}