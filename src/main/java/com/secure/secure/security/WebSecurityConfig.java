package com.secure.secure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        httpSecurity
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->auth
                        .requestMatchers("/api/public/**").permitAll()
                        .requestMatchers("/api/admin/**").authenticated()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user1 = User.withUsername("shubham")
//                .password(passwordEncoder.encode("Shubham@2005"))
//                .roles("USER","ADMIN")
//                .build();
//        UserDetails user2 = User.withUsername("aditya")
//                .password(passwordEncoder.encode("Aditya@2005"))
//                .roles("USER","ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1,user2);
//    }

}
