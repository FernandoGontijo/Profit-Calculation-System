package com.dachser.assessment.profit_calculator.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserLoginResponseDto {
    private String username;
    private Set<String> roles;
    private String token;
}
