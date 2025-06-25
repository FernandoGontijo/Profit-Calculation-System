package com.dachser.assessment.profit_calculator.controller;


import com.dachser.assessment.profit_calculator.dto.response.ProfitLossResponseDto;
import com.dachser.assessment.profit_calculator.service.ProfitLossService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/profit-loss")
@RestController
@RequiredArgsConstructor
public class ProfitLossController {

    private final ProfitLossService profitLossService;

    @GetMapping
    public ResponseEntity<Page<ProfitLossResponseDto>> getAllProfitLoss(Pageable pageable) {
        return ResponseEntity.ok(profitLossService.getAllProfitLoss(pageable));
    }

    @PostMapping("/{shipmentId}")
    public ResponseEntity<ProfitLossResponseDto> getProfitLoss(@PathVariable Long shipmentId) {
        ProfitLossResponseDto profitLoss =  profitLossService.calculate(shipmentId);
        return ResponseEntity.ok(profitLoss);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfitLoss(@PathVariable Long id) {
        profitLossService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
