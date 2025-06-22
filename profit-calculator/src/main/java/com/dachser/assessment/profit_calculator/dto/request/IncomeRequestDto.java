package com.dachser.assessment.profit_calculator.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncomeRequestDto {

    private Long id;
    private Long shipmentId;
    private BigDecimal amount;
}
