package com.example.ecom_backend.services.Auth;

import com.example.ecom_backend.dto.User.AuthRequestDto;
import com.example.ecom_backend.dto.User.CreateUserDto;
import com.example.ecom_backend.dto.User.JwtResponseDto;

public interface AuthService {
  String CreateUser(CreateUserDto user);
  JwtResponseDto login(AuthRequestDto user);
  String getAccessToken(String refreshToken);
}
