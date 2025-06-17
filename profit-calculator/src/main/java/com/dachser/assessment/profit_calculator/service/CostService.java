package com.dachser.assessment.profit_calculator.service;

import com.dachser.assessment.profit_calculator.dto.request.CostRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.CostResponseDto;

import java.util.List;

public interface CostService {

    public List<CostResponseDto> getAllCosts();

    public CostResponseDto getCost(Long id);

    public Long create(CostRequestDto costRequestDto);

    public void delete(Long id);



}
