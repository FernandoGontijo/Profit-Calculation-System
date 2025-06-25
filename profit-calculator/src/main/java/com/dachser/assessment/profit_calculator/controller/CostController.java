package com.dachser.assessment.profit_calculator.controller;

import com.dachser.assessment.profit_calculator.dto.request.CostCreationDto;
import com.dachser.assessment.profit_calculator.dto.request.CostRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.CostResponseDto;
import com.dachser.assessment.profit_calculator.service.CostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/v1/cost")
@RestController
@RequiredArgsConstructor
public class CostController {

    private final CostService costService;

    @PostMapping
    public ResponseEntity<CostResponseDto> create(@RequestBody CostRequestDto request) {
        CostResponseDto cost = costService.create(request);
        return ResponseEntity.ok(cost);
    }

    @GetMapping()
    public ResponseEntity<List<CostResponseDto>> getAllCosts() {
        List<CostResponseDto> costs = costService.getAll();
        return ResponseEntity.ok(costs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostResponseDto> getById(@PathVariable Long id) {
        CostResponseDto cost = costService.getById(id);
        return ResponseEntity.ok(cost);
    }

    @GetMapping("/shipment/{shipmentId}")
    public ResponseEntity<CostResponseDto> getCostByShipment(@PathVariable Long shipmentId) {
        CostResponseDto costs = costService.getByShipmentId(shipmentId);
        return ResponseEntity.ok(costs);
    }

    @PutMapping()
    public ResponseEntity<CostResponseDto> update(@RequestBody CostRequestDto request) {
        CostResponseDto cost = costService.update(request);
        return ResponseEntity.ok(cost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        costService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
