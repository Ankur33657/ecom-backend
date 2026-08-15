package com.example.ecom_backend.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurity {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http){
        http.csrf(csfr->csfr.disable())
                .authorizeHttpRequests(auth->
                        auth
                                .requestMatchers("/api/users/**").hasRole("ADMIN")
                                .requestMatchers("/api/orders/**").hasAnyRole("USER","ADMIN")
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    UserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder){
        UserDetails admin= User.withUsername("Ankur").roles("ADMIN")
                .password(passwordEncoder.encode("Ankur336@"))
                .build();

        UserDetails user= User.withUsername("Arnav").roles("USER")
                .password(passwordEncoder.encode("Arnav336@"))
                .build();
        return new InMemoryUserDetailsManager(admin,user);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
