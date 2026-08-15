package com.example.ecom_backend.services.Users;

import com.example.ecom_backend.dto.User.UserDto;
import com.example.ecom_backend.models.Users;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers(int page,int size,String direction,String sortBy);
    UserDto createUsers(Users user);
    UserDto getUserById(Long id);
}
