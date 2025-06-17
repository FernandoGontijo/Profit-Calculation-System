package com.dachser.assessment.profit_calculator.service.impl;

import com.dachser.assessment.profit_calculator.dto.response.ProfitLossResponseDto;
import com.dachser.assessment.profit_calculator.repository.ProfitLossRepository;
import com.dachser.assessment.profit_calculator.service.ProfitLossService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfitLossServiceImpl implements ProfitLossService {

    private final ProfitLossRepository profitLossRepository;


    @Override
    public List<ProfitLossResponseDto> getAllProfitLoss() {
        return List.of();
    }

    @Override
    public ProfitLossResponseDto getProfitLoss(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
