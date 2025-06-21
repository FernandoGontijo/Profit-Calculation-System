package com.dachser.assessment.profit_calculator.mapper;


import com.dachser.assessment.profit_calculator.dto.request.CostRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.CostResponseDto;
import com.dachser.assessment.profit_calculator.entity.Cost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CostMapper {

    Cost toEntity(CostRequestDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "shipment.id", target = "shipmentId")
    CostResponseDto toDto(Cost cost);
}
