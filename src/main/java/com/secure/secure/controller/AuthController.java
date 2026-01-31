package com.secure.secure.controller;

import com.secure.secure.security.request.LoginRequestDto;
import com.secure.secure.security.response.LoginResponseDto;
import com.secure.secure.security.response.SignUpResponseDto;
import com.secure.secure.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }


    @PostMapping("/signup")
    public ResponseEntity<LoginResponseDto> login(@RequestBody SignUpRequestDto loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }

}
