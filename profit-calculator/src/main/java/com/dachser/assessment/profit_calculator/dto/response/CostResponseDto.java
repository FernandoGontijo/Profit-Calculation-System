package com.dachser.assessment.profit_calculator.dto.response;


import com.dachser.assessment.profit_calculator.model.Shipment;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CostResponseDto {

    private Long shipmentId;
    private BigDecimal amount;
}
