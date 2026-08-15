package com.example.ecom_backend.config;

import com.example.ecom_backend.services.Auth.JwtService;
import com.example.ecom_backend.services.Auth.userAuthService;
import com.example.ecom_backend.services.Users.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final userAuthService userAuthService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String authHeader=request.getHeader("Authorization");
       if(authHeader!=null && authHeader.startsWith("Bearer ")){

           try{
               String token=authHeader.substring(7);
               if(jwtService.validateToken(token)){

                   String username= jwtService.getUsernameFromToken(token);
                   UserDetails userDetails= userAuthService.loadUserByUsername(username);

                   if(SecurityContextHolder.getContext().getAuthentication()==null){
                       UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                       SecurityContextHolder.getContext().setAuthentication(authentication);
                   }

               }
           }catch (Exception e) {
               e.printStackTrace();

               response.setStatus(HttpStatus.UNAUTHORIZED.value());
               return;
           }


       }

       filterChain.doFilter(request,response);

    }
}
