package com.dachser.assessment.profit_calculator.mapper;


import com.dachser.assessment.profit_calculator.dto.response.ProfitLossResponseDto;
import com.dachser.assessment.profit_calculator.model.ProfitLoss;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfitLossMapper {

    ProfitLossResponseDto toDto(ProfitLoss profitLoss);
}
