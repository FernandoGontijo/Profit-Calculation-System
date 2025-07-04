package com.dachser.assessment.profit_calculator.service;

import com.dachser.assessment.profit_calculator.dto.response.ProfitLossResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfitLossService {

    ProfitLossResponseDto calculate(Long id);

    Page<ProfitLossResponseDto> getAllProfitLoss(Pageable pageable);

    void delete(Long id);
}
