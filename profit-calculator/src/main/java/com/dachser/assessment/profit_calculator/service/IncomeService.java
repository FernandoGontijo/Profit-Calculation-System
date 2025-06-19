package com.dachser.assessment.profit_calculator.service;

import com.dachser.assessment.profit_calculator.dto.request.IncomeRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.IncomeResponseDto;

import java.util.List;

public interface IncomeService {

    IncomeResponseDto create(IncomeRequestDto request);

    IncomeResponseDto getById(Long id);

    List<IncomeResponseDto> getByShipmentId(Long shipmentId);

    IncomeResponseDto update(Long id, IncomeRequestDto request);

    void delete(Long id);

}
