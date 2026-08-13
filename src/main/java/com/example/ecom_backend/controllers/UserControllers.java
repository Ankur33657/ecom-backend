package com.example.ecom_backend.controllers;


import com.example.ecom_backend.models.Users;
import com.example.ecom_backend.services.Users.UserService;
import jakarta.validation.Valid;
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
   public List<Users> getALlUsers(){
     return userService.getAllUsers();
   }

    @PostMapping
    public Users createUsers(@Valid @RequestBody Users users) throws Exception{
        return userService.createUsers(users);
   }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id){
       return userService.getUserById(id);

   }


}
