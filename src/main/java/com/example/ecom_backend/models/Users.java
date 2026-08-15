package com.example.ecom_backend.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Value;

@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "username is required")
    @NotNull
    @Size(max=200,min=8,message = "UserName must be between 8 and 200 characters")
    private String userName;


    @NotBlank(message = "email is required")
    @NotNull
    @Column(unique = true)
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "password is required")
    @Pattern( regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$",
            message = "Password must be at least 8 characters long and include uppercase, lowercase, number, and special character")
    private String password;


    @Enumerated(EnumType.STRING)
    private  Role roles;


}
