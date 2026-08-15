package com.example.ecom_backend.services.Auth;


import com.example.ecom_backend.dto.User.CreateUserDto;
import com.example.ecom_backend.models.Role;
import com.example.ecom_backend.models.Users;
import com.example.ecom_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public String CreateUser(CreateUserDto user) {
        Optional<Users> u= userRepository.findByEmail(user.getEmail());
        if(u.isPresent())return "USER ALREADY EXISTS";
        Users newUser=new Users();
        newUser.setUserName(user.getUserName());
        newUser.setEmail(user.getEmail());
        newUser.setRoles(Role.USER);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(newUser);
        return "USER CREATED SUCCESSFULLY";
    }
}
