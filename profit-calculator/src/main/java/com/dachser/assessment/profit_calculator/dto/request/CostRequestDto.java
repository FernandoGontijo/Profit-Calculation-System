package com.dachser.assessment.profit_calculator.dto.request;

import com.dachser.assessment.profit_calculator.entity.Shipment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CostRequestDto {

    private Long id;
    private Long shipmentId;
    private BigDecimal amount;
}
