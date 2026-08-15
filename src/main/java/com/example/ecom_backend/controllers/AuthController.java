package com.example.ecom_backend.controllers;


import com.example.ecom_backend.dto.User.AuthRequestDto;
import com.example.ecom_backend.dto.User.CreateUserDto;
import com.example.ecom_backend.dto.User.JwtResponseDto;
import com.example.ecom_backend.models.Users;
import com.example.ecom_backend.repository.UserRepository;
import com.example.ecom_backend.services.Auth.AuthService;
import com.example.ecom_backend.services.Auth.CustomUserDetailsService;
import com.example.ecom_backend.services.Auth.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
   private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody AuthRequestDto authRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
        String accessToken=jwtService.generateToken(authRequest.getEmail(),true);
        String refreshToken=jwtService.generateToken(authRequest.getEmail(),false);
        Users user =userRepository.findByEmail(authRequest.getEmail()).get();
        return ResponseEntity.status(HttpStatus.OK).body(new JwtResponseDto(accessToken,refreshToken,user));

    }


    @PostMapping("/refresh-token")
    public ResponseEntity<String>  refreshToken(@RequestBody String refreshToken){
    if(jwtService.validateToken(refreshToken)){
        String email=jwtService.getUsernameFromToken(refreshToken);
        //validating the user from DB
        Users user=userRepository.findByEmail(email).get();
        if(user!=null){
            String accessToken=jwtService.generateToken(email,true);
            return ResponseEntity.status(HttpStatus.OK).body(accessToken);

        }
    }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Refresh Token");
    }

    @PostMapping("/signin")
    public ResponseEntity<String> UserSignIn(@RequestBody CreateUserDto user) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(authService.CreateUser(user));
    }



}
