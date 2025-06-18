package com.dachser.assessment.profit_calculator.dto.response;

import lombok.Data;

import java.util.Set;


@Data
public class UserResponseDto {
    private String username;
    private boolean enabled;
    private Set<String> roles;
}
