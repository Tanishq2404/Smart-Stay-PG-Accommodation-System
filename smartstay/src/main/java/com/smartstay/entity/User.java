package com.smartstay.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@NotBlank(message = "Full Name is required")
	@Column(name = "full_name", nullable = false, length = 100)
	private String fullName;

	@NotBlank(message = "Email is required")
	@Email(message = "Enter a valid email")
	@Column(unique = true, nullable = false, length = 100)
	private String email;

	@NotBlank(message = "Password is required")
	@Column(nullable = false, length = 255)
	private String password;

	@NotBlank(message = "Mobile Number is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
	@Column(name = "mobile_number", unique = true, nullable = false, length = 10)
	private String mobileNumber;

	@NotBlank(message = "Gender is required")
	@Column(nullable = false, length = 10)
	private String gender;

    // Default Constructor
    public User() {
    }

    // Parameterized Constructor
    public User(Long userId, String fullName, String email, String password,
                String mobileNumber, String gender) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
    }

    // Getters and Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    // In future we'll encrypt the password using BCrypt
    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}