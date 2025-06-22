package com.dachser.assessment.profit_calculator.mapper;


import com.dachser.assessment.profit_calculator.dto.request.IncomeRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.IncomeResponseDto;
import com.dachser.assessment.profit_calculator.entity.Income;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IncomeMapper {

    Income toEntity(IncomeRequestDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "shipment.id", target = "shipmentId")
    IncomeResponseDto toDto(Income income);

}
