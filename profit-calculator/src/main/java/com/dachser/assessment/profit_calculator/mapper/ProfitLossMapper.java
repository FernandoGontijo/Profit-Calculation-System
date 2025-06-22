package com.dachser.assessment.profit_calculator.mapper;


import com.dachser.assessment.profit_calculator.dto.response.ProfitLossResponseDto;
import com.dachser.assessment.profit_calculator.entity.ProfitLoss;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfitLossMapper {


    @Mapping(source = "shipment.id", target = "shipmentId")
    ProfitLossResponseDto toDto(ProfitLoss profitLoss);
}
