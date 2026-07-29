package com.smartstay.dto;

import jakarta.validation.constraints.NotNull;

public class WishlistRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "PG ID is required")
    private Long pgId;


    // Default Constructor
    public WishlistRequest() {
    }


    // Parameterized Constructor
    public WishlistRequest(Long userId, Long pgId) {
        this.userId = userId;
        this.pgId = pgId;
    }


    // Getters and Setters

    public Long getUserId() {
        return userId;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Long getPgId() {
        return pgId;
    }


    public void setPgId(Long pgId) {
        this.pgId = pgId;
    }
}