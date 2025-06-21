package com.dachser.assessment.profit_calculator.service;

import com.dachser.assessment.profit_calculator.dto.request.ShipmentRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.ShipmentResponseDto;

import java.util.List;

public interface ShipmentService {

    ShipmentResponseDto create();
    ShipmentResponseDto getById(Long id);
    List<ShipmentResponseDto> getAll();
    ShipmentResponseDto update(ShipmentRequestDto request);
    void delete(Long id);
}
