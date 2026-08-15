package com.example.ecom_backend.controllers;


import com.example.ecom_backend.dto.Order.CreateOrderDto;
import com.example.ecom_backend.dto.Order.OrderDto;
import com.example.ecom_backend.dto.User.UserOrderDto;
import com.example.ecom_backend.services.Orders.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    private  final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<OrderDto> CreateOrder(@PathVariable Long userId,@Valid @RequestBody CreateOrderDto order) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.CreateOrder(userId,order));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserOrderDto>> getOrderById(@PathVariable Long userId) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(userId));
    }



}
