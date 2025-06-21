package com.dachser.assessment.profit_calculator.dto.response;

import com.dachser.assessment.profit_calculator.entity.Shipment;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProfitLossResponseDto {

    private Shipment shipment;
    private BigDecimal totalIncome;
    private BigDecimal totalCost;
    private BigDecimal calculatedProfit;
}
