package com.example.ecom_backend.config.security;

import com.example.ecom_backend.config.exceptions.NotFoundException;
import com.example.ecom_backend.models.Users;
import com.example.ecom_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class userAuthService implements UserDetailsService {

   // The spring Security interact with this Class to get User from UserName(Email in this case).
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Users user=userRepository.findByEmail(username).orElseThrow(()->new NotFoundException("User not found"));
       return new CustomUserDetailsService(user);
    }
}
