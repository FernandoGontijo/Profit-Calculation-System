package com.dachser.assessment.profit_calculator.controller;


import lombok.RequiredArgsConstructor;
import com.dachser.assessment.profit_calculator.dto.request.UserLoginRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.UserLoginResponseDto;
import com.dachser.assessment.profit_calculator.service.JwtService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        var user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        Set<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        String token = jwtService.generateToken(user.getUsername(), roles);

        return new UserLoginResponseDto(user.getUsername(), roles, token);
    }
}
