package com.example.ecom_backend.services.Users;

import com.example.ecom_backend.config.exceptions.NotFoundException;
import com.example.ecom_backend.dto.User.UserDto;
import com.example.ecom_backend.models.Role;
import com.example.ecom_backend.models.Users;
import com.example.ecom_backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;
    @Test
    void shouldReturnUserById() {
        //Step-1 Mocking The Data
        Users user=new Users();
        user.setId(1L);
        user.setUserName("ankur336");
        user.setEmail("ankursingh336@gmail.com");


        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        // Step-2 Check if Service Layer work
        UserDto actualUser=userService.getUserById(1L);

        //Step-3 Verify Mock Data With Actual Data
        assertEquals(user.getId(),actualUser.getId());
        assertEquals(user.getUserName(),actualUser.getUserName());
        assertEquals(user.getEmail(),actualUser.getEmail());

        verify(userRepository).findById(1L);

    }

    @Test
    void shouldThrowExceptionWhenUserNotFound(){
        when(userRepository.findById(1L)).thenReturn(Optional.empty());


        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> userService.getUserById(1L)
        );

        assertEquals("User Not found with id: 1", exception.getMessage());

        verify(userRepository).findById(1L);
    }



}