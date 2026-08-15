package com.example.ecom_backend.dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserOrderDto {
    private Long id;
    private String productName;
}
