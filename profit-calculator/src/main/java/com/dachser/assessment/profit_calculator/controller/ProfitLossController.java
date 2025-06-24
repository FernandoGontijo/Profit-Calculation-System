package com.dachser.assessment.profit_calculator.controller;


import com.dachser.assessment.profit_calculator.dto.response.ProfitLossResponseDto;
import com.dachser.assessment.profit_calculator.dto.response.ShipmentResponseDto;
import com.dachser.assessment.profit_calculator.service.ProfitLossService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("/api/v1/profit-loss")
@RestController
@RequiredArgsConstructor
public class ProfitLossController {

    private final ProfitLossService profitLossService;

    @GetMapping
    public ResponseEntity<Page<ProfitLossResponseDto>> getAllProfitLoss(Pageable pageable) {
        return ResponseEntity.ok(profitLossService.getAllProfitLoss(pageable));
    }

    @GetMapping("/{shipmentId}")
    public ResponseEntity<ProfitLossResponseDto> getProfitLoss(@PathVariable Long shipmentId) {
        ProfitLossResponseDto profitLoss =  profitLossService.getProfitLoss(shipmentId);
        return ResponseEntity.ok(profitLoss);
    }

}
