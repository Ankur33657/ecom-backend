package com.example.ecom_backend.dto.User;

import com.example.ecom_backend.models.Users;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseDto {
    private String accessToken;
    private String refreshToken;
    private Users user;
}
