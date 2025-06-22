package com.dachser.assessment.profit_calculator.controller;


import com.dachser.assessment.profit_calculator.dto.request.IncomeRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.CostResponseDto;
import com.dachser.assessment.profit_calculator.dto.response.IncomeResponseDto;
import com.dachser.assessment.profit_calculator.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/v1/income")
@RestController
@RequiredArgsConstructor
public class IncomeController {


    private final IncomeService incomeService;


    @PostMapping
    public ResponseEntity<IncomeResponseDto> create(@RequestBody IncomeRequestDto request) {
        IncomeResponseDto income = incomeService.create(request);
        return ResponseEntity.ok(income);
    }

    @GetMapping()
    public ResponseEntity<List<IncomeResponseDto>> getAllIncomes() {
        List<IncomeResponseDto> incomes = incomeService.getAll();
        return ResponseEntity.ok(incomes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncomeResponseDto> getById(@PathVariable Long id) {
        IncomeResponseDto income = incomeService.getById(id);
        return ResponseEntity.ok(income);
    }

    @GetMapping("/shipment/{shipmentId}")
    public ResponseEntity<List<IncomeResponseDto>> getIncomeByShipment(@PathVariable Long shipmentId) {
        List<IncomeResponseDto> incomes = incomeService.getByShipmentId(shipmentId);
        return ResponseEntity.ok(incomes);
    }

    @PutMapping()
    public ResponseEntity<IncomeResponseDto> update(@RequestBody IncomeRequestDto request) {
        IncomeResponseDto income = incomeService.update(request);
        return ResponseEntity.ok(income);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        incomeService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
