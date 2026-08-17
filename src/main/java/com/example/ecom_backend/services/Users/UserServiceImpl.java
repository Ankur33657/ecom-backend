package com.example.ecom_backend.services.Users;

import com.example.ecom_backend.config.exceptions.NotFoundException;
import com.example.ecom_backend.dto.User.UserDto;
import com.example.ecom_backend.mapper.UsersMapper;
import com.example.ecom_backend.models.Users;
import com.example.ecom_backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(value = "products",key = "#page+'-'+#size+'-'+#direction+'-'+#sortBy")
    @Override
    public List<UserDto> getAllUsers(int page,int size,String direction,String sortBy) {
        log.info("Fetch from db");
        Sort sort=direction.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(page,size,sort);
        Page<Users> userPage=userRepository.findAll(pageable);
        List<UserDto> finalUsers=new ArrayList<>();
        for(Users u:userPage){
            finalUsers.add(UsersMapper.toUserResponse(u));
        }
        return finalUsers;

    }


    @Cacheable(value = "products",key = "#id")
    @Override
    public UserDto getUserById(Long id) {
        log.info("Fetching from db");
        Users user= userRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("User Not found with id: "+id));
        return UsersMapper.toUserResponse(user);
    }
}
