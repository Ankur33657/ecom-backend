package com.example.ecom_backend.services.Auth;

import com.example.ecom_backend.config.security.JwtService;
import com.example.ecom_backend.dto.User.AuthRequestDto;
import com.example.ecom_backend.dto.User.CreateUserDto;
import com.example.ecom_backend.dto.User.JwtResponseDto;
import com.example.ecom_backend.models.Role;
import com.example.ecom_backend.models.Users;
import com.example.ecom_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

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

    @Override
    public JwtResponseDto login(AuthRequestDto user) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        String accessToken=jwtService.generateToken(user.getEmail(),true);
        String refreshToken=jwtService.generateToken(user.getEmail(),false);
        Users u =userRepository.findByEmail(user.getEmail()).get();
        return new JwtResponseDto(accessToken,refreshToken,u);
    }

    @Override
    public String getAccessToken(String refreshToken) {
        try{
            if(jwtService.validateToken(refreshToken)){
                String email=jwtService.getUsernameFromToken(refreshToken);
                //validating the user from DB
                Optional<Users> user=userRepository.findByEmail(email);
                if(user.isPresent()){
                    return jwtService.generateToken(email,true);

                }
            }
        }catch (Exception ex){
            throw ex;
        }
   return "INVALID_ACCESS_TOKEN";

    }
}
