package com.example.ecom_backend.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = "username is required")
    private String userName;


    @NotBlank(message = "email is required")
    @Column(unique = true)
    @Email
    private String email;

    @NotBlank(message = "password is required")
    @Pattern( regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$",
            message = "Password must be at least 8 characters long and include uppercase, lowercase, number, and special character")
    private String password;


    private String roles;


}
