package com.secure.secure.security.service;

import com.secure.secure.enums.AppRole;
import com.secure.secure.model.Role;
import com.secure.secure.model.User;
import com.secure.secure.repository.RoleRepository;
import com.secure.secure.repository.UserRepository;
import com.secure.secure.security.jwt.JwtUtils;
import com.secure.secure.security.request.LoginRequestDto;
import com.secure.secure.security.request.SignUpRequestDto;
import com.secure.secure.security.response.LoginResponseDto;
import com.secure.secure.security.response.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService{

    private final JwtUtils jwtUtils;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    @Override
    public LoginResponseDto login(LoginRequestDto loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
        );

        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String token = jwtUtils.generateJwtToken(userDetails.getUsername());
        return new LoginResponseDto(token);
    }

    @Override
    public SignUpResponseDto signup(SignUpRequestDto signUpRequestDto) {

        if (userRepo.existsByEmail(signUpRequestDto.getEmail())) {
            throw new RuntimeException("User already exists");
        }

        // create user
        List<Role> roles = new ArrayList<>();
        Role userRole = roleRepo
                .findByRoleName(AppRole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));

        roles.add(userRole);
        User user = userRepo.save(User.builder()
                .fullName(signUpRequestDto.getFullName())
                .email(signUpRequestDto.getEmail())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                        .roles(roles)
                .build());

    // return user
        return new SignUpResponseDto(user.getId(), user.getFullName());
    }

}
