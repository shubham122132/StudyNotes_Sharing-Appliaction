    package com.secure.secure.security;

    import com.secure.secure.security.jwt.JwtAuthFilter;
    import lombok.RequiredArgsConstructor;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
    import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.core.userdetails.User;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.provisioning.InMemoryUserDetailsManager;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    @RequiredArgsConstructor
    @EnableMethodSecurity
    public class WebSecurityConfig {

        private final PasswordEncoder passwordEncoder;
        private final JwtAuthFilter jwtAuthFilter;

        @Bean
        public AuthenticationManager authenticationManager(
                AuthenticationConfiguration configuration
        ) throws Exception {
            return configuration.getAuthenticationManager();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
            httpSecurity
                    .csrf(AbstractHttpConfigurer::disable)
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(auth ->auth
                            .requestMatchers("/api/auth/**").permitAll()
                            .requestMatchers("/api/public/**").permitAll()
                            .requestMatchers("/api/admin/**").hasRole("ADMIN")
                            .anyRequest().authenticated()
                    )
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
            return httpSecurity.build();
        }


        // Hardcorded user using inMemoryUserDetailsManager
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
