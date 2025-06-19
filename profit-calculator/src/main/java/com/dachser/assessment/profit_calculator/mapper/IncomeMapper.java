package com.dachser.assessment.profit_calculator.mapper;


import com.dachser.assessment.profit_calculator.dto.request.IncomeRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.IncomeResponseDto;
import com.dachser.assessment.profit_calculator.model.Income;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IncomeMapper {

    @Mapping(source = "shipment.id", target = "shipmentId")
    IncomeResponseDto toDto(Income income);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "shipment", ignore = true)
    Income toEntity(IncomeRequestDto requestDto);

}
