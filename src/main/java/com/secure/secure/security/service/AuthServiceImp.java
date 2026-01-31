package com.secure.secure.security.service;

import com.secure.secure.model.User;
import com.secure.secure.repository.RoleRepository;
import com.secure.secure.repository.UserRepository;
import com.secure.secure.security.jwt.Jwtutils;
import com.secure.secure.security.request.LoginRequestDto;
import com.secure.secure.security.response.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService{

    private final Jwtutils jwtUtils;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final AuthenticationManager authenticationManager;


    @Override
    public LoginResponseDto login(LoginRequestDto loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
        );

        User user = (User)authentication.getPrincipal();
        String token = jwtUtils.generateJwtToken(user);
        return new LoginResponseDto(token,user.getId());
    }
}
