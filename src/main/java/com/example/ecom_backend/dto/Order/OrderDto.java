package com.example.ecom_backend.dto.Order;

import com.example.ecom_backend.dto.User.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private String productName;
    private UserDto user;
}
