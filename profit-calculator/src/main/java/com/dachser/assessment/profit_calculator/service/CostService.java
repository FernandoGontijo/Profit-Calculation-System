package com.dachser.assessment.profit_calculator.service;

import com.dachser.assessment.profit_calculator.dto.request.CostCreationDto;
import com.dachser.assessment.profit_calculator.dto.request.CostRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.CostResponseDto;

import java.util.List;

public interface CostService {


    List<CostResponseDto> getAll();

    CostResponseDto create(CostRequestDto request);

    CostResponseDto getById(Long id);

    CostResponseDto getByShipmentId(Long shipmentId);

    CostResponseDto update(CostRequestDto request);

    void delete(Long id);


}
