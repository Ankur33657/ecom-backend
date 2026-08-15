package com.example.ecom_backend.controllers;


import com.example.ecom_backend.dto.User.AuthRequestDto;
import com.example.ecom_backend.dto.User.CreateUserDto;
import com.example.ecom_backend.dto.User.JwtResponseDto;

import com.example.ecom_backend.services.Auth.AuthService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

   private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody AuthRequestDto authRequest){
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(authRequest));


    }


    @PostMapping("/refresh-token")
    public ResponseEntity<String>  getAccessToken(@RequestBody String refreshToken){

        return ResponseEntity.status(HttpStatus.OK).body(authService.getAccessToken(refreshToken));
    }

    @PostMapping("/signin")
    public ResponseEntity<String> UserSignIn(@RequestBody CreateUserDto user) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(authService.CreateUser(user));
    }



}
