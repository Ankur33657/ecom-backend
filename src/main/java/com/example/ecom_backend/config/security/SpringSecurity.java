package com.example.ecom_backend.config.security;


import com.example.ecom_backend.config.exceptions.UnAuthorizedException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SpringSecurity {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UnAuthorizedException unAuthorizedException;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csfr->csfr.disable())
                .authorizeHttpRequests(auth->
                        auth
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/orders/**").hasRole("USER")
                                .anyRequest().authenticated())
                .exceptionHandling(exception->exception.authenticationEntryPoint(unAuthorizedException))
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

//    @Bean
//    UserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder){
//        UserDetails admin= User.withUsername("Ankur").roles("ADMIN")
//                .password(passwordEncoder.encode("Ankur336@"))
//                .build();
//
//        UserDetails user= User.withUsername("Arnav").roles("USER")
//                .password(passwordEncoder.encode("Arnav336@"))
//                .build();
//        return new InMemoryUserDetailsManager(admin,user);
//    }

    @Bean
   public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }


}
