package com.dachser.assessment.profit_calculator.dto.response;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class CostResponseDto {

    private Long id;
    private Long shipmentId;
    private BigDecimal amount;
}
