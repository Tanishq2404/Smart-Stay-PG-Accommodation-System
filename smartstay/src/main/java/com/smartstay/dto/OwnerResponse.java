package com.smartstay.dto;

public class OwnerResponse {

    private Long ownerId;
    private String fullName;
    private String email;
    private String mobileNumber;

    public OwnerResponse() {
    }

    public OwnerResponse(Long ownerId, String fullName,
                         String email, String mobileNumber) {

        this.ownerId = ownerId;
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}