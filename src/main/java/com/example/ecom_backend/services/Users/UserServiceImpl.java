package com.example.ecom_backend.services.Users;

import com.example.ecom_backend.models.Users;
import com.example.ecom_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users createUsers(Users users) {
        return userRepository.save(users);
    }

    @Override
    public Users getUserById(Long id) {
        return userRepository.getById(id);
    }
}
