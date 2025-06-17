package com.dachser.assessment.profit_calculator.service;

import com.dachser.assessment.profit_calculator.dto.response.ProfitLossResponseDto;

import java.util.List;

public interface ProfitLossService {

    public List<ProfitLossResponseDto> getAllProfitLoss();

    public ProfitLossResponseDto getProfitLoss(Long id);

    public void delete(Long id);
}
