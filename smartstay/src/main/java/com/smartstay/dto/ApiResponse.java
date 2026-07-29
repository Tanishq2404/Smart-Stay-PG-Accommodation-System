package com.smartstay.dto;

public class ApiResponse {

    private boolean success;
    private String message;

    // Default Constructor
    public ApiResponse() {
    }

    // Parameterized Constructor
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getter and Setter for Success

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    // Getter and Setter for Message

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}