package com.dachser.assessment.profit_calculator.dto.request;

import lombok.Data;

@Data
public class UserLoginRequestDto {
    private String username;
    private String password;
}
