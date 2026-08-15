package com.example.ecom_backend.services.Auth;

import com.example.ecom_backend.dto.User.CreateUserDto;

public interface AuthService {
  String CreateUser(CreateUserDto user);
}
