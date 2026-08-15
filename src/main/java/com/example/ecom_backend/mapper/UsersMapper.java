package com.example.ecom_backend.mapper;

import com.example.ecom_backend.dto.User.UserDto;
import com.example.ecom_backend.models.Users;

public class UsersMapper {
    public static UserDto toUserResponse(Users user){
        return new UserDto(user.getId(),user.getUserName(),user.getEmail(),user.getRoles());
    }

}
