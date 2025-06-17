package com.dachser.assessment.profit_calculator.service.impl;

import com.dachser.assessment.profit_calculator.dto.request.CostRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.CostResponseDto;
import com.dachser.assessment.profit_calculator.repository.CostRepository;
import com.dachser.assessment.profit_calculator.service.CostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CostServiceImpl implements CostService {


    private final CostRepository costRepository;


    @Override
    public List<CostResponseDto> getAllCosts() {
        return List.of();
    }

    @Override
    public CostResponseDto getCost(Long id) {
        return null;
    }

    @Override
    public Long create(CostRequestDto costRequestDto) {
        return 0L;
    }

    @Override
    public void delete(Long id) {

    }
}
