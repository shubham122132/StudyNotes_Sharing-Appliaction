package com.secure.secure.security.service;

import com.secure.secure.security.request.LoginRequestDto;
import com.secure.secure.security.request.SignUpRequestDto;
import com.secure.secure.security.response.LoginResponseDto;
import com.secure.secure.security.response.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;


public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequest);

    SignUpResponseDto signup(SignUpRequestDto signUpRequestDto);
}
