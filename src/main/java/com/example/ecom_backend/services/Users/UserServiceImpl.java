package com.example.ecom_backend.services.Users;

import com.example.ecom_backend.config.exceptions.NotFoundException;
import com.example.ecom_backend.dto.User.UserDto;
import com.example.ecom_backend.mapper.UsersMapper;
import com.example.ecom_backend.models.Users;
import com.example.ecom_backend.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers(int page,int size,String direction,String sortBy) {

        Sort sort=direction.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(page,size,sort);
        Page<Users> userPage=userRepository.findAll(pageable);
        List<UserDto> finalUsers=new ArrayList<>();
        for(Users u:userPage){
            finalUsers.add(UsersMapper.toUserResponse(u));
        }
        return finalUsers;

    }

//    @Override
//    public UserDto createUsers(Users user) {Users user1=userRepository.save(user);
//      return UsersMapper.toUserResponse(user1);
//
//
//
//    }

    @Override
    public UserDto getUserById(Long id) {
        Users user= userRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("User Not found with id: "+id));
        return UsersMapper.toUserResponse(user);
    }
}
