package com.example.ecom_backend.services.Orders;

import com.example.ecom_backend.config.exceptions.NotFoundException;
import com.example.ecom_backend.dto.Order.CreateOrderDto;
import com.example.ecom_backend.dto.Order.OrderDto;
import com.example.ecom_backend.dto.User.UserOrderDto;
import com.example.ecom_backend.mapper.OrderMapper;
import com.example.ecom_backend.models.Orders;
import com.example.ecom_backend.models.Users;
import com.example.ecom_backend.repository.OrderRepository;
import com.example.ecom_backend.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final UserRepository userRepository;  //construction injection
    private final OrderRepository orderRepository;



    @Override

    @Transactional  // roolback when atomicity fails
    public OrderDto CreateOrder(Long userId, CreateOrderDto order) {
        Users user= userRepository.findById(userId).orElseThrow(()->new NotFoundException("User Not Found with id: "+userId));
        Orders newOrder = new Orders();
        newOrder.setProductName(order.getProductName());
        newOrder.setUser(user);
        orderRepository.save(newOrder);
        return OrderMapper.toOrderResponse(newOrder);

    }

    @Override
    public List<UserOrderDto> getOrderById(Long userId) {
        List<Orders> order=orderRepository.findByUserId(userId);
        List<UserOrderDto>li=new ArrayList<>();
        for(Orders o:order){
          li.add(OrderMapper.toUserOrderResponse(o));
        }
       return li;
    }
}
