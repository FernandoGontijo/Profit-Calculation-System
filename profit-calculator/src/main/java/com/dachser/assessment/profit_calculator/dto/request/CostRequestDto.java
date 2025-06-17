package com.dachser.assessment.profit_calculator.dto.request;

import com.dachser.assessment.profit_calculator.model.Shipment;

import java.math.BigDecimal;

public class CostRequestDto {

    private Shipment shipment;
    private BigDecimal amount;
}
