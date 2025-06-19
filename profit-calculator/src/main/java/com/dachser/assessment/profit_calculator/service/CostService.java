package com.dachser.assessment.profit_calculator.service;

import com.dachser.assessment.profit_calculator.dto.request.CostRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.CostResponseDto;

import java.util.List;

public interface CostService {


    CostResponseDto create(CostRequestDto request);

    CostResponseDto getById(Long id);

    List<CostResponseDto> getByShipmentId(Long shipmentId);

    CostResponseDto update(Long id, CostRequestDto request);

    void delete(Long id);


}
