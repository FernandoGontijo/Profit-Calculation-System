package com.dachser.assessment.profit_calculator.service;

import com.dachser.assessment.profit_calculator.dto.request.IncomeRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.IncomeResponseDto;

import java.util.List;

public interface IncomeService {

    public List<IncomeResponseDto> getAllIncome();

    public IncomeResponseDto getIncome(Long id);

    public Long create(IncomeRequestDto incomeRequestDto);

    public void delete(Long id);
}
