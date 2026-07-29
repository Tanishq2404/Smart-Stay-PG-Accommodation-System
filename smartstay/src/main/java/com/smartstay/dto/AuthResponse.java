package com.smartstay.dto;

public class AuthResponse {

    private boolean success;
    private String message;
    private Object data;

    // Default Constructor
    public AuthResponse() {
    }

    // Parameterized Constructor
    public AuthResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
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

    // Getter and Setter for Data

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}