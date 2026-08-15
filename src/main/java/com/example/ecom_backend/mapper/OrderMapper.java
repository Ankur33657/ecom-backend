package com.example.ecom_backend.mapper;

import com.example.ecom_backend.dto.Order.OrderDto;
import com.example.ecom_backend.dto.User.UserOrderDto;
import com.example.ecom_backend.models.Orders;

public class OrderMapper {
    public static OrderDto toOrderResponse(Orders order){
        return new OrderDto(order.getId(),order.getProductName(),UsersMapper.toUserResponse(order.getUser()));
    }

    public static UserOrderDto toUserOrderResponse(Orders order){
        return new UserOrderDto(order.getId(),order.getProductName());
    }
}
