package com.example.ecom_backend.services.Orders;

import com.example.ecom_backend.dto.Order.CreateOrderDto;
import com.example.ecom_backend.dto.Order.OrderDto;
import com.example.ecom_backend.dto.User.UserOrderDto;

import java.util.List;

public interface OrderService {
    OrderDto CreateOrder(Long userId, CreateOrderDto order);
    List<UserOrderDto> getOrderById(Long userId);
}
