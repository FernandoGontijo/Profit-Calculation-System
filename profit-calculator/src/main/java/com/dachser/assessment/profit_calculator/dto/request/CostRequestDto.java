package com.dachser.assessment.profit_calculator.dto.request;

import com.dachser.assessment.profit_calculator.model.Shipment;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CostRequestDto {

    private Shipment shipment;
    private BigDecimal amount;
}
