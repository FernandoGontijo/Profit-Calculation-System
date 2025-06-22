package com.dachser.assessment.profit_calculator.controller;


import com.dachser.assessment.profit_calculator.dto.response.ProfitLossResponseDto;
import com.dachser.assessment.profit_calculator.dto.response.ShipmentResponseDto;
import com.dachser.assessment.profit_calculator.service.ProfitLossService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/profit-loss")
@RestController
@RequiredArgsConstructor
public class ProfitLossController {

    private final ProfitLossService profitLossService;


    @GetMapping("/{shipmentId}")
    public ResponseEntity<ProfitLossResponseDto> calculate(@PathVariable Long shipmentId) {
        ProfitLossResponseDto profitLoss =  profitLossService.calculate(shipmentId);
        return ResponseEntity.ok(profitLoss);
    }

}
