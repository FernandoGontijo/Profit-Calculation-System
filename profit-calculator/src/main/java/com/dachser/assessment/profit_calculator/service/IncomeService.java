package com.dachser.assessment.profit_calculator.service;

import com.dachser.assessment.profit_calculator.dto.request.IncomeRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.IncomeResponseDto;

import java.util.List;

public interface IncomeService {

    List<IncomeResponseDto> getAll();

    IncomeResponseDto create(IncomeRequestDto request);

    IncomeResponseDto getById(Long id);

    IncomeResponseDto getByShipmentId(Long shipmentId);

    IncomeResponseDto update(IncomeRequestDto request);

    void delete(Long id);

}
