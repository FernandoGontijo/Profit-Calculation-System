package com.dachser.assessment.profit_calculator.service.impl;

import com.dachser.assessment.profit_calculator.dto.request.IncomeRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.IncomeResponseDto;
import com.dachser.assessment.profit_calculator.repository.IncomeRepository;
import com.dachser.assessment.profit_calculator.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;


    @Override
    public List<IncomeResponseDto> getAllIncome() {
        return List.of();
    }

    @Override
    public IncomeResponseDto getIncome(Long id) {
        return null;
    }

    @Override
    public Long create(IncomeRequestDto incomeRequestDto) {
        return 0L;
    }

    @Override
    public void delete(Long id) {

    }
}
