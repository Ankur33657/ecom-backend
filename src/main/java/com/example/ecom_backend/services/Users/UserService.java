package com.example.ecom_backend.services.Users;

import com.example.ecom_backend.models.Users;

import java.util.List;

public interface UserService {
    List<Users> getAllUsers();
    Users createUsers(Users users);
    Users getUserById(Long id);
}
