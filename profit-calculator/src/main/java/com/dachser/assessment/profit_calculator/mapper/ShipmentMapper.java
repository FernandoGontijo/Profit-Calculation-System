package com.dachser.assessment.profit_calculator.mapper;

import com.dachser.assessment.profit_calculator.dto.request.ShipmentRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.ShipmentResponseDto;
import com.dachser.assessment.profit_calculator.model.Shipment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShipmentMapper {

    Shipment toEntity(ShipmentRequestDto requestDto);

    ShipmentResponseDto toDto(Shipment shipment);
}
