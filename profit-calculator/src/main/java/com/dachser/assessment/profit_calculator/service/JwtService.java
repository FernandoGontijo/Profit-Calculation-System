package com.dachser.assessment.profit_calculator.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

public interface JwtService {

    String generateToken(String username, Set<String> roles);

    String extractUsername(String token);

    Set<String> extractRoles(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
