package com.example.ecom_backend.dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String userName;
    private String email;
    private String role;
}
