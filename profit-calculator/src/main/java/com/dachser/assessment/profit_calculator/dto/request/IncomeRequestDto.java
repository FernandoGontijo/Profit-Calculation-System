package com.dachser.assessment.profit_calculator.dto.request;

import com.dachser.assessment.profit_calculator.model.Shipment;

import java.math.BigDecimal;

public class IncomeRequestDto {

    private Long id;
    private Shipment shipment;
    private BigDecimal amount;
}
