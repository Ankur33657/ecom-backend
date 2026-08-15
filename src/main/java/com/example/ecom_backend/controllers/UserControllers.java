package com.example.ecom_backend.controllers;


import com.example.ecom_backend.dto.User.UserDto;
import com.example.ecom_backend.models.Users;
import com.example.ecom_backend.services.Users.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserControllers {

    private final UserService userService;

    public UserControllers(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
   public ResponseEntity<List<UserDto>> getALlUsers(@RequestParam int page,@RequestParam(defaultValue = "10") int size, @RequestParam(required = false, defaultValue = "asc") String direction,@RequestParam(required = false, defaultValue = "id") String sortBy) throws Exception{
     return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers(page,size,direction,sortBy));
   }

//    @PostMapping
//    public ResponseEntity<UserDto> createUsers(@Valid @RequestBody Users user) throws Exception{
//       return  ResponseEntity.status(HttpStatus.CREATED).body(userService.createUsers(user));
//   }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) throws Exception{
       return ResponseEntity.status(HttpStatus.OK) .body(userService.getUserById(id));

   }


}
